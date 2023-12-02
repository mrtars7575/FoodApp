package com.example.foodorderapp.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.foodorderapp.data.entity.Basket;
import com.example.foodorderapp.data.entity.BasketResponse;
import com.example.foodorderapp.data.entity.CRUDResponse;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.entity.FoodResponse;
import com.example.foodorderapp.retrofit.AppDao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppDaoRepository {
    public MutableLiveData<List<Food>> foodList = new MutableLiveData();
    public MutableLiveData<List<Basket>> basketList = new MutableLiveData<>();
    private AppDao appDao;
    private FirebaseAuth auth;
    public String username;
    public List<Basket> baskets;
    @Inject
    public AppDaoRepository(AppDao appDao,FirebaseAuth auth) {
        this.appDao = appDao;
        this.auth = auth;
        if (auth!=null){
            username = auth.getCurrentUser().getDisplayName();
        }

    }

    public void getAllFood(){
        appDao.getAllFood().enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                List<Food> foods = response.body().getFoodList();
                foodList.setValue(foods);

            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                System.out.println("get all food error");
            }
        });
    }

    public void getAllFoodInBasket(){
        appDao.getAllFoodInBasket(username).enqueue(new Callback<BasketResponse>() {
            @Override
            public void onResponse(Call<BasketResponse> call, Response<BasketResponse> response) {
                System.out.println("get all food in basket");
                System.out.println("basket" + response.body().getSuccess());
                System.out.println(response.body().getBasketList());
                baskets = response.body().getBasketList();
                basketList.setValue(baskets);
            }

            @Override
            public void onFailure(Call<BasketResponse> call, Throwable t) {
                System.out.println("basket error");
            }
        });
    }

    public void deleteFoodFromBasket(int basketId,String userName){
        appDao.deleteFoodFromBasket(basketId,userName).enqueue(new Callback<CRUDResponse>() {
            @Override
            public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                System.out.println("delete " + response.body().getMessage() );
                getAllFoodInBasket();
            }

            @Override
            public void onFailure(Call<CRUDResponse> call, Throwable t) {

            }
        });
    }

    public void insertFoodToBasket(String foodName,String foodImageName,int foodPrice,int foodQuantity,String userName){
        boolean isFoodAdded = false;
        List<Basket> foods = basketList.getValue();
        if (foods != null && !foods.isEmpty()) {
            for (Basket food : foods) {
                if (food.getFoodName().equals(foodName)) {
                    isFoodAdded = true;
                    foodQuantity = foodQuantity + food.getFoodQuantity();
                    addToBasketToApi(foodName,foodImageName,foodPrice,foodQuantity,userName);
                    deleteFoodFromBasket(food.getBasketId(),userName);
                }
            }
        }
        if (!isFoodAdded) {
            addToBasketToApi(foodName,foodImageName,foodPrice,foodQuantity,userName);
        }

    }



    public void addToBasketToApi(String foodName,String foodImageName,int foodPrice,int foodQuantity,String userName){
        appDao.insertFoodToBasket(foodName,foodImageName,foodPrice,foodQuantity,userName)
                .enqueue(new Callback<CRUDResponse>() {
                    @Override
                    public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                        System.out.println("insert  add to basket api " + response.body().getSuccess());
                        System.out.println("insert  add to basket ap " + response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<CRUDResponse> call, Throwable t) {
                        System.out.println("insert food to basket error add to basket api");
                    }
                });
    }

}
