package com.example.foodorderapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.foodorderapp.R;
import com.example.foodorderapp.data.entity.Basket;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.databinding.FragmentHomeBinding;
import com.example.foodorderapp.ui.activity.AuthOperationActivity;
import com.example.foodorderapp.ui.adapter.food.FoodAdapter;
import com.example.foodorderapp.ui.adapter.food.IFoodAdapterItemClickListener;
import com.example.foodorderapp.ui.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements IFoodAdapterItemClickListener {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private FoodAdapter adapter;
    public List<Food> favoriteList;

    boolean isButtonClicked;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);

        binding.foodRv.setLayoutManager(new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false));

        favoriteList =new ArrayList<>();

        String username = viewModel.getCurrentUsername();

        binding.userNameTv.setText("Welcome "+username);

        viewModel.foodList.observe(getViewLifecycleOwner(),foods -> {

            adapter =new FoodAdapter(foods,requireContext(),viewModel,favoriteList,this);
            binding.foodRv.setAdapter(adapter);

        });

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signOut();

                Intent intent = new Intent(getActivity(), AuthOperationActivity.class);
                startActivity(intent);

            }
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

        viewModel.favoriteList.observe(getViewLifecycleOwner(),foods -> {
            favoriteList = foods;
            adapter.updateFavoriteList(foods);
        });

        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllFood();
    }

    @Override
    public void onClickAddButton(Food food) {
        isButtonClicked = false;
        Snackbar.make(binding.getRoot(), "Add " + food.getFoodName() +"to basket?", Snackbar.LENGTH_LONG)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isButtonClicked == false) {
                            isButtonClicked = true;
                            viewModel.insertFoodToBasket(
                                    food.getFoodName(),
                                    food.getFoodImageName(),
                                    food.getFoodPrice(),
                                    1);
                            Toast.makeText(requireContext(), "Added to basket", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
    }

    @Override
    public void onClickFood(Food food) {

    }

    @Override
    public void onClickFavoriteToogleButton(Food food, Boolean isChecked) {
        if (isChecked){
            viewModel.addToFavorite(food);
        }else{
            viewModel.deleteToFavorite(food);

        }
    }
}