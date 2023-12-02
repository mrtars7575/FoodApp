package com.example.foodorderapp.data.entity;

public class Favorite {
    private Food food;
    private String userName;

    public Favorite() {
    }

    public Favorite(Food food, String userName) {
        this.food = food;
        this.userName = userName;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
