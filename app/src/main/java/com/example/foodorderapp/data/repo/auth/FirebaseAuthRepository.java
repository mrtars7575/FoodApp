package com.example.foodorderapp.data.repo.auth;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class FirebaseAuthRepository implements IFirebaseAuth {

    private FirebaseAuth mAuth;
    public MutableLiveData<Boolean> userLogged = new MutableLiveData<>();

    @Inject
    public FirebaseAuthRepository(FirebaseAuth auth) {
        this.mAuth = auth;
    }

    @Override
    public void signUp(String userName, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, (OnCompleteListener<AuthResult>) new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    System.out.println("createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    userLogged.setValue(true);
                    System.out.println("user : " + user);
                } else {
                    // If sign in fails, display a message to the user.
                    System.out.println("createUserWithEmail:failure"+ task.getException());

                }
            }
        });




    }
    @Override
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        System.out.println("signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        userLogged.setValue(true);
                        System.out.println("user :" + user);


                    } else {
                        // If sign in fails, display a message to the user.
                        System.out.println( "signInWithEmail:failure" + task.getException());


                    }
                });
    }

    @Override
    public void signOut() {
        userLogged.setValue(false);
        mAuth.signOut();
    }
}
