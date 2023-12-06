package com.example.foodorderapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.foodorderapp.R;
import com.example.foodorderapp.databinding.ActivityMainBinding;
import com.example.foodorderapp.ui.fragment.FavoriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setupNav();

        setContentView(binding.getRoot());
    }

    private void setupNav(){
        binding.bottomAppBar.clearFocus();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomAppBar,navController);




        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                List<Integer> visiblePages = Arrays.asList(R.id.homeFragment,R.id.favoriteFragment,R.id.basketFragment);


                if (visiblePages.contains(navDestination.getId())) {
                    binding.bottomAppBar.setVisibility(View.VISIBLE);
                } else {
                    binding.bottomAppBar.setVisibility(View.GONE);
                }
            }
        });




    }

}