package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderapp.databinding.FragmentBasketBinding;
import com.example.foodorderapp.ui.adapter.basket.BasketAdapter;
import com.example.foodorderapp.ui.viewmodel.BasketViewModel;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private BasketViewModel viewModel;
    private BasketAdapter adapter;

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

        /*utils = new Utils();
        utils.bottomNavInActivity(requireActivity());*/

        binding.basketRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.basketList.observe(getViewLifecycleOwner(),baskets -> {

            adapter = new BasketAdapter(baskets,getContext(),viewModel);
            binding.basketRv.setAdapter(adapter);

        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllFoodInBasket();
    }
}