package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodorderapp.databinding.FragmentFirstBinding;

import com.example.foodorderapp.R;
import com.example.foodorderapp.ui.viewmodel.FirstViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private FirstViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FirstViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        binding = FragmentFirstBinding.inflate(inflater,container,false);


        /*utils = new Utils();
        utils.bottomNavInActivity(requireActivity());*/

        binding.goToSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_signInFragment);
            }
        });


        binding.goToSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_signUpFragment);
            }
        });
        return binding.getRoot();
    }
}