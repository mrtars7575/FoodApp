package com.example.foodorderapp.di;

import android.content.Context;

import com.example.foodorderapp.data.repo.AppDaoRepository;
import com.example.foodorderapp.retrofit.ApiUtils;
import com.example.foodorderapp.retrofit.AppDao;

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
    public AppDaoRepository provideAppDaoRepository(AppDao appDao){
        return new AppDaoRepository(appDao);
    }

    @Provides
    @Singleton
    public AppDao provideAppDao(){
        return ApiUtils.getAppDao();
    }




}
