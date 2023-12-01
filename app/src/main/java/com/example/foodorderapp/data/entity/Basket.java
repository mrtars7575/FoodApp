package com.example.foodorderapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Basket implements Serializable {
    @SerializedName("sepet_yemek_id")
    private int basketId;
    @SerializedName("yemek_adi")
    private String foodName;
    @SerializedName("yemek_resim_adi")
    private String foodImageName;
    @SerializedName("yemek_fiyat")
    private int foodPrice;
    @SerializedName("yemek_siparis_adet")
    private int foodQuantity;
    @SerializedName("kullanici_adi")
    private String userName;


    public Basket() {
    }

    public Basket(int basketId, String foodName, String foodImageName, int foodPrice, int foodQuantity, String userName) {
        this.basketId = basketId;
        this.foodName = foodName;
        this.foodImageName = foodImageName;
        this.foodPrice = foodPrice;
        this.foodQuantity = foodQuantity;
        this.userName = userName;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
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

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
