package com.example.foodorderapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodorderapp.R;
import com.example.foodorderapp.databinding.ActivityAuthOperationBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthOperationActivity extends AppCompatActivity {

    private ActivityAuthOperationBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthOperationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }
}