package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.repo.auth.FirebaseAuthRepository;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignUpViewModel extends ViewModel {

    public FirebaseAuthRepository authRepository;
    public FirebaseAuth auth;
    public MutableLiveData<Boolean> userLogged;

    @Inject
    public SignUpViewModel(FirebaseAuthRepository authRepository, FirebaseAuth auth) {
        this.authRepository = authRepository;
        this.auth = auth;
        this.userLogged = authRepository.userLogged;

    }


    public void signUp(String username,String email,String password){
        authRepository.signUp(username,email,password);
    }
}
