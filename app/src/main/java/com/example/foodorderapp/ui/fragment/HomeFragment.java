package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.databinding.FragmentHomeBinding;
import com.example.foodorderapp.ui.adapter.FoodAdapter;
import com.example.foodorderapp.ui.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private FoodAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);

        binding.foodRv.setLayoutManager
                (new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false));

        viewModel.foodList.observe(getViewLifecycleOwner(),foods -> {
            adapter =new FoodAdapter(foods,requireContext(),viewModel);
            binding.foodRv.setAdapter(adapter);

        });

        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("charSequence : " + charSequence );
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllFood();
    }
}