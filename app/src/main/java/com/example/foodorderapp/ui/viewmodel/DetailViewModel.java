package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.repo.AppDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {

    public MutableLiveData<Integer> foodQuantity = new MutableLiveData<>(1);
    public AppDaoRepository appDaoRepository;

    @Inject
    public DetailViewModel(AppDaoRepository appDaoRepository) {
        this.appDaoRepository = appDaoRepository;
    }

    public void increaseQuantity(){
        foodQuantity.setValue(foodQuantity.getValue() + 1);
    }

    public void decreaseQunatity(){
        if (foodQuantity.getValue()>1){
            foodQuantity.setValue(foodQuantity.getValue() - 1);
        }
    }

    public void insertFoodToBasket(String foodName,String foodImageName,int foodPrice,int foodQuantity,String userName){
        appDaoRepository.insertFoodToBasket(foodName,foodImageName,foodPrice,foodQuantity,userName);
    }
}
