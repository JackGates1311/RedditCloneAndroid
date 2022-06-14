package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.JsonPlaceholderAPI;
import com.example.sr2_2020_android2021_projekat.model.Community;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CommunityFragment extends Fragment {

    private String communityNameParam;

    private TextView communityName;

    private TextView communityDescription;

    private Button showAllCommunityPostsButton;

    private JsonPlaceholderAPI jsonPlaceholderAPI;

    public static CommunityFragment newInstance(String communityNameParam) {

        return new CommunityFragment(communityNameParam);
    }

    public CommunityFragment(String communityNameParam) {

        this.communityNameParam = communityNameParam;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Community details");

        View view = inflater.inflate(R.layout.fragment_community, container, false);

        communityName = view.findViewById(R.id.community_info_name);

        communityDescription = view.findViewById(R.id.community_info_description);

        showAllCommunityPostsButton = view.findViewById(R.id.show_community_posts_button);

        showAllCommunityPostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransition.to(CommunityPostsFragment.newInstance(communityNameParam), getActivity(), true,
                        R.id.viewPage);

            }
        });

        communityName.setText(communityNameParam);

        getCommunityByName();

        return view;
    }

    private void getCommunityByName() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/").
                addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<Community> call = jsonPlaceholderAPI.getCommunityByName(communityNameParam);

        call.enqueue(new Callback<Community>() {

            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {

                if(!response.isSuccessful()) {

                    // Errors 400 and 500

                    Toast.makeText(getContext(), "HTTP returned code " + response.code(),
                            Toast.LENGTH_LONG).show();

                    return;
                }

                Community community = response.body();

                communityDescription.setText(community.getDescription());



            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}