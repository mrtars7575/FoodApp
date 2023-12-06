package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderapp.R;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.databinding.FragmentFavoriteBinding;
import com.example.foodorderapp.ui.adapter.favorite.FavoriteAdapter;
import com.example.foodorderapp.ui.adapter.favorite.IFavoriteAdapaterItemClickListener;
import com.example.foodorderapp.ui.viewmodel.FavoriteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteFragment extends Fragment implements IFavoriteAdapaterItemClickListener {

    private FragmentFavoriteBinding binding;
    private FavoriteViewModel viewModel;
    private FavoriteAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater,container,false);

        /*utils = new Utils();
        utils.bottomNavActivity(requireActivity());*/

        binding.favoriteRv.setLayoutManager
                (new GridLayoutManager(getContext(),1, LinearLayoutManager.VERTICAL,false));

        viewModel.favoriteList.observe(getViewLifecycleOwner(),foods -> {
            adapter = new FavoriteAdapter(foods,requireContext(),this);
            binding.favoriteRv.setAdapter(adapter);
        });


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadAllFavorities();
    }

    @Override
    public void onClickFood(Food food) {

    }

    @Override
    public void onClickFavoriteToggleButton(Food food, Boolean isChecked) {
        if (isChecked){
            viewModel.addToFavorite(food);
        }else{
            viewModel.deleteToFavorite(food);
        }
    }
}