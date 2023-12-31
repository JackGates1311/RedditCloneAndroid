package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.Routes;
import com.example.sr2_2020_android2021_projekat.model.LoginRequest;
import com.example.sr2_2020_android2021_projekat.model.AuthResponse;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.EnvironmentConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    private com.google.android.material.textfield.TextInputEditText username;
    private com.google.android.material.textfield.TextInputEditText password;

    private Button loginButton;

    private Routes routes;

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

                    FragmentTransition.to(PostsFragment.newInstance("hot"), getActivity(),
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl(EnvironmentConfig.baseURL).
                addConverterFactory(GsonConverterFactory.create(gson)).build();

        routes = retrofit.create(Routes.class);

        LoginRequest loginRequest = new LoginRequest(username.getText().toString(),
                password.getText().toString());

        Call<AuthResponse> call = routes.login(loginRequest);

        call.enqueue(new Callback<AuthResponse>() {

            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                if(!response.isSuccessful()) {

                    ((MainActivity)getActivity()).storeDataToSharedPreferences(null,
                            0, null);

                    Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();

                    return;
                }

                AuthResponse authResponse = response.body();

                String authToken = authResponse.getAuthToken();
                int expiresIn = authResponse.getExpiresIn();
                String username = loginRequest.getUsername();

                ((MainActivity)getActivity()).storeDataToSharedPreferences(
                        authToken, expiresIn, username);

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }

        });

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
