package com.example.foodorderapp.di;

import com.example.foodorderapp.data.repo.auth.FirebaseAuthRepository;
import com.example.foodorderapp.data.repo.auth.IFirebaseAuth;
import com.example.foodorderapp.data.repo.database.FirebaseDatabaseRepository;
import com.example.foodorderapp.data.repo.database.IFirebaseDatabaseDao;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FirebaseModule {
    @Provides
    public FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }


    @Provides
    @Singleton
    public IFirebaseAuth provideFirebaseAuthRepository(FirebaseAuth auth) {
        return new FirebaseAuthRepository(auth);
    }

    @Provides
    @Singleton
    public DatabaseReference provideDatabaseReference(){
        return FirebaseDatabase.getInstance().getReference();
    }


    @Provides
    @Singleton
    public IFirebaseDatabaseDao provideFirebaseDatabaseRepository(DatabaseReference reference,FirebaseAuth auth) {
        return new FirebaseDatabaseRepository(reference,auth);
    }



}
