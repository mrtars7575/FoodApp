package com.example.foodorderapp.ui.adapter.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.data.entity.Food;
import com.example.foodorderapp.databinding.FoodRecyclerViewBinding;
import com.example.foodorderapp.ui.fragment.HomeFragmentDirections;
import com.example.foodorderapp.ui.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> implements Filterable {

    private List<Food> foodList;
    private Context mContext;
    private HomeViewModel viewModel;
    private List<Food> filteredList;
    private List<Food> favoriteList;
    private IFoodAdapterItemClickListener listener;

    public FoodAdapter(List<Food> foodList, Context mContext, HomeViewModel viewModel,List<Food> favoriteList,IFoodAdapterItemClickListener listener) {
        this.foodList = foodList;
        this.mContext = mContext;
        this.viewModel = viewModel;
        this.filteredList = new ArrayList<>(foodList);
        this.favoriteList = favoriteList;
        this.listener = listener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
               String query = charSequence.toString().toLowerCase();

               List<Food> filtered = new ArrayList<>();

                for (Food food : foodList) {
                    if (food.getFoodName().toLowerCase().contains(query)) {
                        filtered.add(food);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList.clear();
                filteredList.addAll((List<Food>) filterResults.values);
                System.out.println("filtered list");
                System.out.println(filteredList);
                System.out.println("filtered list 1 ");
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private FoodRecyclerViewBinding binding;

        public ViewHolder(FoodRecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodRecyclerViewBinding binding =FoodRecyclerViewBinding.inflate(LayoutInflater.from(mContext),
                parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        Food food = filteredList.get(position);
        holder.binding.foodNameTv.setText(food.getFoodName());
        holder.binding.foodPriceTv.setText(String.valueOf(food.getFoodPrice()));

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + food.getFoodImageName();
        Glide.with(mContext).load(url).into(holder.binding.foodIv);



        holder.binding.foodCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToDetailFragment action =
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(food);

                Navigation.findNavController(view).navigate(action);
            }
        });


        // toggle button click
        holder.binding.favoriteToggleButton.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                System.out.println("is checked : " + b);
                // different icon
            }else{
                System.out.println("is checked : " + b);
                //
            }

            listener.onClickFavoriteToogleButton(food,b);

        });



        //check toggle button
        if(favoriteList.contains(food)){
            // icon
            holder.binding.favoriteToggleButton.setChecked(true);
        }else{
            holder.binding.favoriteToggleButton.setChecked(false);
        }



    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
}
