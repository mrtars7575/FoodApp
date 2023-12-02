package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.repo.database.FirebaseDatabaseRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FavoriteViewModel extends ViewModel {

    private FirebaseDatabaseRepository firebaseDatabaseRepository;
    public MutableLiveData<List<Food>> favoriteList;

    @Inject
    public FavoriteViewModel(FirebaseDatabaseRepository firebaseDatabaseRepository) {
        this.firebaseDatabaseRepository = firebaseDatabaseRepository;
        favoriteList = firebaseDatabaseRepository.favoriteLiveData;
        loadAllFavorities();
    }

    public void loadAllFavorities(){
        firebaseDatabaseRepository.loadAllFavorites();
    }

    public void addToFavorite(Food food){
        firebaseDatabaseRepository.addFavorite(food);
    }

    public void deleteToFavorite(Food food){
        firebaseDatabaseRepository.deleteFavorite(food);
    }

}
