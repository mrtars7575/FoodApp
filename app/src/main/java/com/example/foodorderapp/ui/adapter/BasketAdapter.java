package com.example.foodorderapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.data.entity.Basket;
import com.example.foodorderapp.databinding.BasketRecyclerViewBinding;
import com.example.foodorderapp.databinding.FoodRecyclerViewBinding;
import com.example.foodorderapp.ui.viewmodel.BasketViewModel;


import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private List<Basket> basketList;
    private Context mContext;
    private BasketViewModel viewModel;

    public BasketAdapter(List<Basket> basketList, Context mContext, BasketViewModel viewModel) {
        this.basketList = basketList;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private BasketRecyclerViewBinding binding;

        public ViewHolder(BasketRecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }

    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BasketRecyclerViewBinding binding =BasketRecyclerViewBinding.inflate(LayoutInflater.from(mContext),
                parent,false);

        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull BasketAdapter.ViewHolder holder, int position) {

        Basket basket = basketList.get(position);

        System.out.println("basket food name " + basket.getFoodName());


        holder.binding.basketFoodPriceTv.setText(String.valueOf(basket.getFoodPrice()));
        holder.binding.basketFoodNameTv.setText(basket.getFoodName().toString());
        holder.binding.basketFoodQuantityTv.setText(String.valueOf(basket.getFoodQuantity()));

        int totalPrice = basket.getFoodPrice() * basket.getFoodQuantity();

        holder.binding.basketFoodTotalPriceTv.setText(String.valueOf(totalPrice));

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + basket.getFoodImageName();
        Glide.with(mContext).load(url).into(holder.binding.basketFoodIv);


        holder.binding.deleteBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteFoodFromBasket(basket.getBasketId(),"Murat");
            }
        });


    }

    @Override
    public int getItemCount() {
        return basketList.size();
    }
}
