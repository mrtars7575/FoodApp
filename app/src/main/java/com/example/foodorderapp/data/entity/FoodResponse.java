package com.example.foodorderapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {
    @SerializedName("yemekler")
    private List<Food> foodList;
    @SerializedName("success")
    private int success;


    public FoodResponse() {
    }

    public FoodResponse(List<Food> foodList, int success) {

        this.foodList = foodList;
        this.success = success;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
