package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.JsonPlaceholderAPI;
import com.example.sr2_2020_android2021_projekat.model.Community;
import com.example.sr2_2020_android2021_projekat.model.PostRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateEditPostFragment extends Fragment {

    private com.google.android.material.textfield.TextInputEditText title;
    private com.google.android.material.textfield.TextInputEditText text;

    private AutoCompleteTextView postCommunities;

    private Button createPostButton;

    private JsonPlaceholderAPI jsonPlaceholderAPI;

    public static CreateEditPostFragment newInstance() {

        return new CreateEditPostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_edit_post, container, false);

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        title = view.findViewById(R.id.title);
        text = view.findViewById(R.id.text);

        postCommunities = view.findViewById(R.id.postCommunities);

        createPostButton = view.findViewById(R.id.button_create_post);

        getAllCommunities(view);

        createPostButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                boolean isTitleValid = title.getText().toString().length() > 0;
                boolean isTextValid = text.getText().toString().length() > 0;

                if(!isTitleValid) {

                    Toast.makeText(getContext(), "Please provide a valid post title",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isTextValid) {

                    Toast.makeText(getContext(), "Please provide a valid post text",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                createPost();

            }

        });


        if(((MainActivity)getActivity()).postMode == "ADD") {

            getActivity().setTitle("Create new post");

            view.findViewById(R.id.button_create_post).setVisibility(View.VISIBLE);

            view.findViewById(R.id.button_save_changes_post).setVisibility(View.GONE);

        } else {

            getActivity().setTitle("Edit post");

            view.findViewById(R.id.button_create_post).setVisibility(View.GONE);

            view.findViewById(R.id.button_save_changes_post).setVisibility(View.VISIBLE);
        }

        return view;

    }

    private void getAllCommunities(View view) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/").
                addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<Community>> call = jsonPlaceholderAPI.getAllCommunities();

        call.enqueue(new Callback<List<Community>>() {

            @Override
            public void onResponse(Call<List<Community>> call, Response<List<Community>> response) {

                if(!response.isSuccessful()) {

                    // Errors 400 and 500

                    Toast.makeText(getContext(), "HTTP returned code " + response.code(),
                            Toast.LENGTH_LONG).show();
                }

                List<String> communityNames = new ArrayList<>();

                assert response.body() != null;

                for(Community community : response.body()) {

                    communityNames.add(community.getName());
                }

                ArrayAdapter<String> adapter = new
                        ArrayAdapter<>(getContext(), R.layout.drop_down_item, communityNames);

                AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.postCommunities);

                autoCompleteTextView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Community>> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void createPost() {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/").
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create(gson)).build();

        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        PostRequest postRequest = new PostRequest(postCommunities.getText().toString(), "",
                text.getText().toString(), title.getText().toString());

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getContext());

        String authToken = "Bearer " + preferences.getString("authToken", null);

        Call<String> call = jsonPlaceholderAPI.createPost(authToken, postRequest);

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(!response.isSuccessful()) {

                    Toast.makeText(getContext(), "HTTP returned code " + response.code(),
                            Toast.LENGTH_LONG).show();

                    return;
                }

                Toast.makeText(getContext(), "Post successfully created",
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




    }
}