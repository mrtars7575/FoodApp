package com.example.foodorderapp.data.repo.database;

import com.example.foodorderapp.data.entity.Food;

public interface IFirebaseDatabaseDao {
    void addFavorite(Food food);
    void getAllFavorites();
    void deleteFavorite(Food food);
}
