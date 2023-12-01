package com.example.foodorderapp.retrofit;

public class ApiUtils {
    public static final String BASE_URL = "http://kasimadalan.pe.hu/";

    public static AppDao getAppDao(){
        return RetrofitClient.getClient(BASE_URL)
                .create(AppDao.class);
    }
}
