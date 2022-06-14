package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.adapters.PostRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.JsonPlaceholderAPI;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityPostsFragment extends Fragment {

    private String communityNameParam;


    public static CommunityPostsFragment newInstance(String communityNameParam) {

        return new CommunityPostsFragment(communityNameParam);
    }

    public CommunityPostsFragment(String communityNameParam) {

        this.communityNameParam = communityNameParam;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(true,
                true);

        getActivity().setTitle("Posts");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_homepage).setChecked(true);

        View view = inflater.inflate(R.layout.fragment_community_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPostsByCommunityName(recyclerView);

        return view;
    }

    private void getPostsByCommunityName(RecyclerView recyclerView) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/").
                addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<PostResponse>> call = jsonPlaceholderAPI.getPostsByCommunityName(
                communityNameParam);

        call.enqueue(new Callback<List<PostResponse>>() {

            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {

                if(!response.isSuccessful()) {

                    // Errors 400 and 500

                    Toast.makeText(getContext(), "HTTP returned code " + response.code(),
                            Toast.LENGTH_LONG).show();

                    return;
                }

                recyclerView.setAdapter(new PostRecyclerAdapter(getActivity(), response.body()));

            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
