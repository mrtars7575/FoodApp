package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SplashViewModel extends ViewModel {

    private FirebaseAuth auth;

    @Inject
    public SplashViewModel(FirebaseAuth auth) {
        this.auth = auth;
    }


    public Boolean checkAuth(){
        if (auth.getCurrentUser()!=null){
            return true;
        }else{
            return false;
        }
    }
}
