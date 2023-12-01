package com.example.foodorderapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodorderapp.R;
import com.example.foodorderapp.databinding.FragmentSignInBinding;
import com.example.foodorderapp.ui.viewmodel.SignInViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;
    private SignInViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater,container,false);



        binding.signInButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String email = binding.emailEt.getText().toString();
                String password = binding.passwordEt.getText().toString();

                if (!email.isEmpty()&&!password.isEmpty()){
                    viewModel.signIn(email,password);
                    viewModel.userLogged.observe(getViewLifecycleOwner(),value -> {
                        System.out.println("value " + value);
                        if(value){
                            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_homeFragment);
                        }
                    });
                }


            }
        });




        return binding.getRoot();
    }
}