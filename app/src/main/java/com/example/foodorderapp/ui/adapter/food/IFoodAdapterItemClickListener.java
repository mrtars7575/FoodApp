package com.example.foodorderapp.ui.adapter.food;

import com.example.foodorderapp.data.entity.Food;

public interface IFoodAdapterItemClickListener {

    void onClickAddButton(Food food);
    void onClickFood(Food food);
    void onClickFavoriteToogleButton(Food food,Boolean isChecked);

}
