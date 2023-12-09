package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderapp.R;
import com.example.foodorderapp.databinding.FragmentSignUpBinding;
import com.example.foodorderapp.ui.viewmodel.SignUpViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false);


        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.usernameEt.getText().toString();
                String email = binding.emailSignUpEt.getText().toString();
                String password = binding.passwordSignUpEt.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    viewModel.signUp(username,email,password);
                    Navigation.findNavController(view)
                            .navigate(R.id.action_signUpFragment_to_signInFragment);
                }


            }
        });


        return binding.getRoot();
    }
}