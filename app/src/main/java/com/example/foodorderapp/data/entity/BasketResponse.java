package com.example.foodorderapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasketResponse {
    @SerializedName("sepet_yemekler")
    private List<Basket> basketList;
    @SerializedName("success")
    private int success;

    public BasketResponse() {
    }

    public BasketResponse(List<Basket> basketList, int success) {
        this.basketList = basketList;
        this.success = success;
    }

    public List<Basket> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Basket> basketList) {
        this.basketList = basketList;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
