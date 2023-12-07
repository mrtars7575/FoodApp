package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.repo.AppDaoRepository;
import com.example.foodorderapp.data.repo.database.FirebaseDatabaseRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {

    public MutableLiveData<Integer> foodQuantity = new MutableLiveData<>(1);
    public AppDaoRepository appDaoRepository;
    public FirebaseDatabaseRepository firebaseDatabaseRepository;
    private FirebaseAuth auth;
    private String username ;
    public MutableLiveData<List<Food>> favoriteList;
    public MutableLiveData<Food> food;

    @Inject
    public DetailViewModel(AppDaoRepository appDaoRepository,FirebaseAuth auth,FirebaseDatabaseRepository firebaseDatabaseRepository) {
        this.appDaoRepository = appDaoRepository;
        this.auth = auth;
        this.firebaseDatabaseRepository = firebaseDatabaseRepository;
        username = auth.getCurrentUser().getDisplayName();
        favoriteList = firebaseDatabaseRepository.favoriteLiveData;
    }

    public void increaseQuantity(){
        foodQuantity.setValue(foodQuantity.getValue() + 1);
    }

    public void decreaseQuantity(){
        if (foodQuantity.getValue()>1){
            foodQuantity.setValue(foodQuantity.getValue() - 1);
        }
    }

    public Food getFood(){
        return food.getValue();
    }

    public void insertFoodToBasket(String foodName,String foodImageName,int foodPrice,int foodQuantity){
        appDaoRepository.insertFoodToBasket(foodName,foodImageName,foodPrice,foodQuantity,username);
    }

    public void loadAllFavorities(){
        firebaseDatabaseRepository.loadAllFavorites();
    }

    public void addToFavorite(Food food){
        firebaseDatabaseRepository.addFavorite(food);
    }

    public void deleteFromFavorite(Food food){
        firebaseDatabaseRepository.deleteFavorite(food);
    }



}
