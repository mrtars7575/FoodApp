package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderapp.data.entity.Basket;
import com.example.foodorderapp.databinding.FragmentBasketBinding;
import com.example.foodorderapp.ui.adapter.basket.BasketAdapter;
import com.example.foodorderapp.ui.viewmodel.BasketViewModel;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private BasketViewModel viewModel;
    private BasketAdapter adapter;
    private List<Basket> basketList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(BasketViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater,container,false);


        binding.basketRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.basketList.observe(getViewLifecycleOwner(),baskets -> {

            int listSize = 0;
            adapter = new BasketAdapter(baskets,getContext(),viewModel);
            binding.basketRv.setAdapter(adapter);
            listSize = baskets.size();
            basketList = baskets;

            if (listSize> 0){
                viewModel.basketFoodTotalPrice.observe(getViewLifecycleOwner(),integer -> {
                    binding.allBasketTotalPriceTv.setText(String.valueOf(integer) + " ₺");

                });

            }else{
                binding.allBasketTotalPriceTv.setText("0 ₺");

            }

        });



        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(binding.getRoot(), "Confirm Basket", Snackbar.LENGTH_LONG).show();
            }
        });


        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllFoodInBasket();
    }
}