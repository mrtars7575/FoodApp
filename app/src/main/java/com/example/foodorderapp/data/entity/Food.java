package com.example.foodorderapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Food implements Serializable {
    @SerializedName("yemek_id")
    private int foodId;
    @SerializedName("yemek_adi")
    private String foodName;
    @SerializedName("yemek_resim_adi")
    private String foodImageName;
    @SerializedName("yemek_fiyat")
    private int foodPrice;

    public Food() {
    }

    public Food(int foodId, String foodName, String foodImageName, int foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImageName = foodImageName;
        this.foodPrice = foodPrice;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImageName() {
        return foodImageName;
    }

    public void setFoodImageName(String foodImageName) {
        this.foodImageName = foodImageName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }
}
