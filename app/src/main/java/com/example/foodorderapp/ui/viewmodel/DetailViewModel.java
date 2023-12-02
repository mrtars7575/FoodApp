package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.repo.AppDaoRepository;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {

    public MutableLiveData<Integer> foodQuantity = new MutableLiveData<>(1);
    public AppDaoRepository appDaoRepository;
    private FirebaseAuth auth;
    private String username ;

    @Inject
    public DetailViewModel(AppDaoRepository appDaoRepository,FirebaseAuth auth) {
        this.appDaoRepository = appDaoRepository;
        this.auth = auth;
        username = auth.getCurrentUser().getDisplayName();
    }

    public void increaseQuantity(){
        foodQuantity.setValue(foodQuantity.getValue() + 1);
    }

    public void decreaseQuantity(){
        if (foodQuantity.getValue()>1){
            foodQuantity.setValue(foodQuantity.getValue() - 1);
        }
    }

    public void insertFoodToBasket(String foodName,String foodImageName,int foodPrice,int foodQuantity){
        appDaoRepository.insertFoodToBasket(foodName,foodImageName,foodPrice,foodQuantity,username);
    }
}
