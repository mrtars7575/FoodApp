package com.example.foodorderapp.data.repo.auth;



import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


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
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        System.out.println("createUserWithEmail:success");
                        
                        FirebaseUser user = mAuth.getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(userName)
                                .build();

                        user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                String name = user.getDisplayName();
                                                System.out.println("user profile update :" + name);
                                            }
                                        });

                        
                    } else {
                        // If sign in fails, display a message to the user.
                        System.out.println("createUserWithEmail:failure"+ task.getException());

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
