package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.entity.Basket;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.repo.AppDaoRepository;
import com.example.foodorderapp.data.repo.database.FirebaseDatabaseRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class BasketViewModel extends ViewModel {


    public AppDaoRepository appDaoRepository;
    public MutableLiveData<List<Basket>> basketList;
    public FirebaseDatabaseRepository firebaseDatabaseRepository;
    private FirebaseAuth auth;
    private String username ;


    public MutableLiveData<Integer> basketFoodTotalPrice = new MutableLiveData<>();

    @Inject
    public BasketViewModel(AppDaoRepository appDaoRepository,FirebaseDatabaseRepository firebaseDatabaseRepository,FirebaseAuth auth) {
        this.appDaoRepository = appDaoRepository;
        this.firebaseDatabaseRepository = firebaseDatabaseRepository;
        this.auth = auth;
        username = auth.getCurrentUser().getDisplayName();
        getAllFoodInBasket();
        basketList = appDaoRepository.basketList;
        totalPriceObserve();
    }

    public void getAllFoodInBasket(){
        appDaoRepository.getAllFoodInBasket();
    }

    public void deleteFoodFromBasket(int basketId){
        appDaoRepository.deleteFoodFromBasket(basketId,username);
    }

    private void totalPriceObserve(){
        basketList.observeForever(baskets -> {
            int totalPrice = 0;

            if (baskets!=null){
                for(Basket basket : baskets){
                    totalPrice += basket.getFoodPrice() * basket.getFoodQuantity();
                }

                basketFoodTotalPrice.setValue(totalPrice);
            }else{
                basketFoodTotalPrice.setValue(0);
            }

        });
    }


}
