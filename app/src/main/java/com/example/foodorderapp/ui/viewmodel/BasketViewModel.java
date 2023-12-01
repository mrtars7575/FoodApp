package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.entity.Basket;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.repo.AppDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BasketViewModel extends ViewModel {

    public AppDaoRepository appDaoRepository;
    public MutableLiveData<List<Basket>> basketList;

    @Inject
    public BasketViewModel(AppDaoRepository appDaoRepository) {
        this.appDaoRepository = appDaoRepository;
        getAllFoodInBasket("Murat");
        basketList = appDaoRepository.basketList;
    }

    public void getAllFoodInBasket(String userName){
        appDaoRepository.getAllFoodInBasket(userName);
    }

    public void deleteFoodFromBasket(int basketId,String userName){
        appDaoRepository.deleteFoodFromBasket(basketId,"Murat");
    }
}
