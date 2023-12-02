package com.example.foodorderapp.ui.adapter.favorite;

import com.example.foodorderapp.data.entity.Food;

public interface IFavoriteAdapaterItemClickListener {

    void onClickFood(Food food);
    void onClickFavoriteToggleButton(Food food, Boolean isChecked);
    
}
