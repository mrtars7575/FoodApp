package com.example.foodorderapp.ui.adapter.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.R;
import com.example.foodorderapp.data.entity.Favorite;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.databinding.FavoriteRecyclerViewBinding;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>  {


    private List<Food> favoriteList;
    private Context mContext;
    private IFavoriteAdapaterItemClickListener listener;

    public FavoriteAdapter(List<Food> favoriteList, Context mContext,IFavoriteAdapaterItemClickListener listener) {
        this.favoriteList = favoriteList;
        this.mContext = mContext;
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private FavoriteRecyclerViewBinding binding;

        public ViewHolder(FavoriteRecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FavoriteRecyclerViewBinding binding = FavoriteRecyclerViewBinding.inflate(LayoutInflater.from(mContext),
                parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Food favoriteFood = favoriteList.get(position);
        holder.binding.favoriteFoodNameTv.setText(favoriteFood.getFoodName());
        holder.binding.favoriteRvFoodPriceTv.setText(String.valueOf(favoriteFood.getFoodPrice()) + " ₺");

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + favoriteFood.getFoodImageName();
        Glide.with(mContext).load(url).into(holder.binding.favoriteFoodIv);

        holder.binding.favoriteToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //
                    holder.binding.favoriteToggleButton.setBackgroundResource(R.drawable.heart2);
                }else{
                    //
                    holder.binding.favoriteToggleButton.setBackgroundResource(R.drawable.heart);
                }

                listener.onClickFavoriteToggleButton(favoriteFood,b);
            }
        });


    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}
