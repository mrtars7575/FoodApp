package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderapp.R;
import com.example.foodorderapp.databinding.FragmentSplashBinding;
import com.example.foodorderapp.ui.viewmodel.SplashViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashFragment extends Fragment {

   private FragmentSplashBinding binding;
   private SplashViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(inflater,container,false);



        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkAuth(getView());
            }
        },2500);

        return binding.getRoot();
    }

    public void checkAuth(View view){
        if (viewModel.checkAuth()){
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
        }else{
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_firstFragment);
        }
    }

}