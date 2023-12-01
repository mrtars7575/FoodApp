package com.example.foodorderapp.retrofit;

import com.example.foodorderapp.data.entity.BasketResponse;
import com.example.foodorderapp.data.entity.CRUDResponse;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.data.entity.FoodResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppDao {

    @GET("yemekler/tumYemekleriGetir.php")
    Call<FoodResponse> getAllFood();

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDResponse> insertFoodToBasket(
            @Field("yemek_adi") String foodName,
            @Field("yemek_resim_adi") String foodImageName,
            @Field("yemek_fiyat") int foodPrice,
            @Field("yemek_siparis_adet") int foodQuantity,
            @Field("kullanici_adi") String userName
    );

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDResponse> deleteFoodFromBasket(
            @Field("sepet_yemek_id") int basketId,
            @Field("kullanici_adi") String userName
    );

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<BasketResponse> getAllFoodInBasket(
            @Field("kullanici_adi") String userName
    );



}
