package com.example.foodorderapp.data.repo.database;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.foodorderapp.data.entity.Food;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FirebaseDatabaseRepository implements IFirebaseDatabaseDao {

    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private String username;
    public MutableLiveData<List<Food>> favoriteLiveData = new MutableLiveData<>();

    @Inject
    public FirebaseDatabaseRepository(DatabaseReference databaseReference, FirebaseAuth auth) {
        this.databaseReference = databaseReference;
        this.auth = auth;
        this.username = auth.getCurrentUser().getDisplayName();

        loadAllFavorites();
    }

    public void loadAllFavorites(){
        getAllFavorites();
    }

    @Override
    public void addFavorite(Food food) {
        databaseReference.child("Favorites")
                .child(username)
                .child(String.valueOf(food.getFoodId()))
                .setValue(food)
                .addOnCompleteListener(task -> System.out.println("add favorite successful"))
                .addOnFailureListener(e -> System.out.println("add favorite failure"));
    }

    @Override
    public void getAllFavorites() {
        databaseReference.child("Favorites")
                .child(username)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Food> favoriteFoods = new ArrayList<>();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Food food = dataSnapshot.getValue(Food.class);
                            favoriteFoods.add(food);
                        }
                        favoriteLiveData.setValue(favoriteFoods);

                        System.out.println("get all favorites  ::::");
                        System.out.println(favoriteLiveData);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("get all favorites problem");
                    }
                });
    }

    @Override
    public void deleteFavorite(Food food) {
        databaseReference.child("Favorites")
                .child(username)
                .child(String.valueOf(food.getFoodId()))
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        System.out.println("favorite delete successful");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("favorite delete failure");
                    }
                });
    }
}
