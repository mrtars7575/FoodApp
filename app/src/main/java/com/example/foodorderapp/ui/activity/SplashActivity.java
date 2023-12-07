package com.example.foodorderapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.foodorderapp.R;
import com.example.foodorderapp.databinding.ActivitySplashBinding;
import com.example.foodorderapp.ui.viewmodel.SplashViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkAuth();
            }
        },5000);



        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void checkAuth(){
        if (viewModel.checkAuth()){
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            // avigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
        }else{
            Intent intent = new Intent(SplashActivity.this, AuthOperationActivity.class);
            startActivity(intent);
            // Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_firstFragment);
        }
    }
}