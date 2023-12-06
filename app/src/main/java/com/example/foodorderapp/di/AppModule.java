package com.example.foodorderapp.di;

import android.content.Context;

import com.example.foodorderapp.data.repo.AppDaoRepository;
import com.example.foodorderapp.retrofit.ApiUtils;
import com.example.foodorderapp.retrofit.AppDao;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public AppDaoRepository provideAppDaoRepository(AppDao appDao,FirebaseAuth auth){
        return new AppDaoRepository(appDao,auth);
    }

    @Provides
    @Singleton
    public AppDao provideAppDao(){
        return ApiUtils.getAppDao();
    }




}
