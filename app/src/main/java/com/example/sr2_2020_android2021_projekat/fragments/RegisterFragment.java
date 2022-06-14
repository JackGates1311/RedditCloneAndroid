package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.JsonPlaceholderAPI;
import com.example.sr2_2020_android2021_projekat.model.RegisterUser;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterFragment extends Fragment {

    private com.google.android.material.textfield.TextInputEditText username;
    private com.google.android.material.textfield.TextInputEditText displayName;
    private com.google.android.material.textfield.TextInputEditText email;
    private com.google.android.material.textfield.TextInputEditText password;
    private com.google.android.material.textfield.TextInputEditText passwordRepeated;
    private com.google.android.material.textfield.TextInputEditText description;

    private Button registerButton;

    private JsonPlaceholderAPI jsonPlaceholderAPI;

    public static RegisterFragment newInstance() {

        return new RegisterFragment();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Register to Reddit Clone");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_register).setChecked(true);

        View view = inflater.inflate(R.layout.fragment_register, vg, false);

        username = view.findViewById(R.id.username);
        displayName = view.findViewById(R.id.displayName);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        passwordRepeated = view.findViewById(R.id.passwordRepeated);
        description = view.findViewById(R.id.description);

        registerButton = view.findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUsernameValid = username.getText().toString().length() > 0;
                boolean isDisplayNameValid = displayName.getText().toString().length() > 0;
                boolean isEmailValid = email.getText().toString().length() > 0 ||
                        email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                boolean isPasswordValid = password.getText().toString().length() > 0;
                boolean isRepeatedPasswordValid = password.getText().toString().length() > 0 &&
                        passwordRepeated.getText().toString().equals(password.getText().toString());
                boolean isDescriptionValid = description.getText().toString().length() > 0;


                if(!isUsernameValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid username",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isDisplayNameValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid display name",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isEmailValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid email",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isPasswordValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid password",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isRepeatedPasswordValid) {

                    Toast.makeText(view.getContext(), "Passwords are not matching",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isDescriptionValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid description",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                register();

            }
        });

        return view;
    }

    private void register() {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/").
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create(gson)).build();

        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        RegisterUser registerUser = new RegisterUser(username.getText().toString(),
                password.getText().toString(), email.getText().toString(), "",
                description.getText().toString(), displayName.getText().toString());

        Call<String> call = jsonPlaceholderAPI.register(registerUser);

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(!response.isSuccessful()) {

                    Toast.makeText(getContext(), "HTTP returned code " + response.code(),
                            Toast.LENGTH_LONG).show();

                    return;
                }

                Toast.makeText(getContext(), "User registration is successful",
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Toast.makeText(getActivity(), "onAttach()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Toast.makeText(getActivity(), "onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //Toast.makeText(getActivity(), "onDeatach()", Toast.LENGTH_SHORT).show();
    }

}
