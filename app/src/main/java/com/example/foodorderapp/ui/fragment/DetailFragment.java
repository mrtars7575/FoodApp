package com.example.foodorderapp.ui.fragment;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.R;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.databinding.FragmentDetailBinding;
import com.example.foodorderapp.ui.viewmodel.DetailViewModel;
import com.google.android.material.snackbar.Snackbar;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private DetailViewModel viewModel;

    int totalPrice;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDetailBinding.inflate(inflater,container,false);

        DetailFragmentArgs bundle = DetailFragmentArgs.fromBundle(getArguments());
        Food food =bundle.getFood();


        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + food.getFoodImageName();
        Glide.with(getContext())
                .load(url)
                .into(binding.detailFoodIv);

        viewModel.foodQuantity.observe(getViewLifecycleOwner(),foodQunatity -> {
            // binding.detailFoodPriceTv.setText(String.valueOf(food.getFoodPrice() + " ₺"));
            binding.detailQuantityTv.setText(String.valueOf(foodQunatity));

            totalPrice = food.getFoodPrice() * foodQunatity;
            binding.detailFoodPriceTv.setText(String.valueOf(totalPrice));

        });




        binding.detailFoodNameTv.setText(food.getFoodName());

        binding.increaseBtn.setOnClickListener(view -> viewModel.increaseQuantity());

        binding.decreaseBtn.setOnClickListener(view -> viewModel.decreaseQuantity());

        binding.addBasketBtn.setOnClickListener(view -> {
            viewModel.insertFoodToBasket(food.getFoodName(), food.getFoodImageName(),food.getFoodPrice()
                    ,viewModel.foodQuantity.getValue());

            Snackbar.make(binding.getRoot(), "Added to basket", Snackbar.LENGTH_LONG).show();
            NavHostFragment.findNavController(this).navigateUp();

        });

        viewModel.favoriteList.observe(getViewLifecycleOwner(),foods -> {

            if (foods.contains(food)){
                binding.detailToggleButton.setBackgroundResource(R.drawable.heart2);
                binding.detailToggleButton.setChecked(true);
            }else{
                boolean mBool = false;
                binding.detailToggleButton.setBackgroundResource(R.drawable.heart);
                binding.detailToggleButton.setChecked(mBool);
            }
        });

        binding.detailToggleButton.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                viewModel.addToFavorite(food);
            }else{
                viewModel.deleteFromFavorite(food);
            }
        });

        return binding.getRoot();
    }
}