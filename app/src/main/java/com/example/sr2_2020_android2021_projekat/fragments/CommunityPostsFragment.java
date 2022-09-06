package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.adapters.PostRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.model.ReactionDTO;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class CommunityPostsFragment extends Fragment {

    private final RetrofitRepository<List<PostResponse>> retrofitRepository =
            new RetrofitRepository<>();
    private final HttpClient httpClient = new HttpClient();

    private View view = null;

    private final String communityNameParam;
    private final String sortBy;

    List<ReactionDTO> reactions = new ArrayList<>();


    public static CommunityPostsFragment newInstance(String communityNameParam, String sortBy) {

        return new CommunityPostsFragment(communityNameParam, sortBy);
    }

    public CommunityPostsFragment(String communityNameParam, String sortBy) {

        this.communityNameParam = communityNameParam;
        this.sortBy = sortBy;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if(getActivity() != null) {
            ((MainActivity)getActivity()).setGroupMenuVisibility(true,
                    true);
            ((MainActivity)getActivity()).setSortByMode("communityPosts");
            ((MainActivity)getActivity()).setCommunityNameParam(communityNameParam);
        }


        getActivity().setTitle("Posts");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_homepage).setChecked(true);

        view = inflater.inflate(R.layout.fragment_community_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPostsByCommunityName(recyclerView);

        return view;
    }

    private void getPostsByCommunityName(RecyclerView recyclerView) {

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getContext());

        if(preferences.getString("username", null) != null) {

            RetrofitRepository<List<ReactionDTO>> reactionDTORetrofitRepository = new RetrofitRepository<>();

            reactionDTORetrofitRepository.sendRequest(httpClient.routes.getReactionsByUsername(), view,
                    () -> {
                        reactions = reactionDTORetrofitRepository.getResponseData();
                    });
        }


        retrofitRepository.sendRequest(httpClient.routes.getPostsByCommunityName(
                communityNameParam, sortBy), view, () -> {

            List<PostResponse> postResponse = retrofitRepository.getResponseData();

            recyclerView.setAdapter(new PostRecyclerAdapter(getActivity(), postResponse, reactions,
                    preferences.getString("username", null), view));

        });

    }
}
