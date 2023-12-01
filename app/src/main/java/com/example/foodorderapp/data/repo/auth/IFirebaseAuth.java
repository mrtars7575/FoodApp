package com.example.foodorderapp.data.repo.auth;

public interface IFirebaseAuth {
    void signUp(String userName,String email,String password);
    void signIn(String email,String password);
    void signOut();
}
