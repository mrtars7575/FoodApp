package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.repo.AppDaoRepository;
import com.example.foodorderapp.data.repo.database.FirebaseDatabaseRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    public AppDaoRepository appDaoRepository;
    public MutableLiveData<List<Food>> foodList;
    public MutableLiveData<List<Food>> favoriteList;
    public FirebaseDatabaseRepository firebaseDatabaseRepository;

    @Inject
    public HomeViewModel(AppDaoRepository appDaoRepository,FirebaseDatabaseRepository firebaseDatabaseRepository) {
        this.appDaoRepository = appDaoRepository;
        this.firebaseDatabaseRepository = firebaseDatabaseRepository;
        getAllFood();
        foodList =appDaoRepository.foodList;
        favoriteList = firebaseDatabaseRepository.favoriteLiveData;
    }

    public void getAllFood(){
        appDaoRepository.getAllFood();
    }


    public void addToFavorite(Food food){
        firebaseDatabaseRepository.addFavorite(food);
    }

    public void deleteToFavorite(Food food){
        firebaseDatabaseRepository.deleteFavorite(food);
    }

    public void loadFavorite(){
        favoriteList = firebaseDatabaseRepository.favoriteLiveData;
    }

}
