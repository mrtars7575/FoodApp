package com.example.foodorderapp.di;

import com.example.foodorderapp.data.repo.auth.FirebaseAuthRepository;
import com.example.foodorderapp.data.repo.auth.IFirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;

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
}
