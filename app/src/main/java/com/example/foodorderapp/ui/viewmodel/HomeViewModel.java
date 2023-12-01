package com.example.foodorderapp.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.repo.AppDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    public AppDaoRepository appDaoRepository;
    public MutableLiveData<List<Food>> foodList;

    @Inject
    public HomeViewModel(AppDaoRepository appDaoRepository) {
        this.appDaoRepository = appDaoRepository;
        getAllFood();
        foodList =appDaoRepository.foodList;
    }

    public void getAllFood(){
        appDaoRepository.getAllFood();
    }


}
