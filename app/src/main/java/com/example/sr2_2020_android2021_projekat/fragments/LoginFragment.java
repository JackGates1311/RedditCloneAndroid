package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.JsonPlaceholderAPI;
import com.example.sr2_2020_android2021_projekat.model.LoginRequest;
import com.example.sr2_2020_android2021_projekat.model.LoginResponse;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginFragment extends Fragment {

    private com.google.android.material.textfield.TextInputEditText username;
    private com.google.android.material.textfield.TextInputEditText password;

    private Button loginButton;

    private JsonPlaceholderAPI jsonPlaceholderAPI;

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup vg,
                             @Nullable Bundle data) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Login to Reddit Clone");

        View view = inflater.inflate(R.layout.fragment_login, vg, false);

        username = view.findViewById(R.id.loginUsername);
        password = view.findViewById(R.id.loginPassword);

        loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //clearDataFromSharedPreferences();

                login();

                //pref.getInt("key_name", 0); // getting Integer from shared preferences

                SharedPreferences preferences = PreferenceManager.
                        getDefaultSharedPreferences(getContext());

                if(preferences.getString("authToken", null) != null) {

                    setAppDrawer();

                    FragmentTransition.to(PostsFragment.newInstance(), getActivity(),
                            true, R.id.viewPage);

                    System.out.println(preferences.getString("authToken", null));

                }


            }
        });


        //Toast.makeText(getActivity(), "onAttach()", Toast.LENGTH_SHORT).show();
        return view;
    }

    private void setAppDrawer() {

        ((MainActivity) getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_register).setVisible(false);

        ((MainActivity) getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_login).setVisible(false);

        ((MainActivity) getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_logout).setVisible(true);

        ((MainActivity) getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_manage).setVisible(true);

        ((MainActivity) getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_create_community).setVisible(true);

        ((MainActivity) getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_communities).setVisible(true);

        ((MainActivity) getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_administrator_tools).setVisible(true);

        ((MainActivity) getActivity()).menu.findItem(R.id.action_add_post).setVisible(true);


    }

    private void login() {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/").
                addConverterFactory(GsonConverterFactory.create(gson)).build();

        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        LoginRequest loginRequest = new LoginRequest(username.getText().toString(),
                password.getText().toString());

        Call<LoginResponse> call = jsonPlaceholderAPI.login(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(!response.isSuccessful()) {

                    storeDataToSharedPreferences(null, 0);

                    Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();

                    return;
                }

                LoginResponse loginResponse = response.body();

                String authToken = loginResponse.getAuthToken();
                int expiresIn = loginResponse.getExpiresIn();

                storeDataToSharedPreferences(authToken, expiresIn);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }

        });

    }

    private void storeDataToSharedPreferences(String authToken, int expiresIn) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("authToken", authToken);
        editor.putInt("expiresIn", expiresIn);

        editor.apply();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
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
