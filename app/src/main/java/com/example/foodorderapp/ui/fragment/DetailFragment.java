package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.R;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.databinding.FragmentDetailBinding;
import com.example.foodorderapp.ui.viewmodel.DetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private DetailViewModel viewModel;


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
            binding.detailFoodPriceTv.setText(String.valueOf(food.getFoodPrice()));
            binding.detailQuantityTv.setText(String.valueOf(foodQunatity));
        });

        binding.detailFoodNameTv.setText(food.getFoodName());

        binding.increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.increaseQuantity();
            }
        });

        binding.decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.decreaseQuantity();
            }
        });

        binding.addBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.insertFoodToBasket(food.getFoodName(), food.getFoodImageName(),food.getFoodPrice()
                        ,viewModel.foodQuantity.getValue());

                Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_basketFragment);
            }
        });


        return binding.getRoot();
    }
}