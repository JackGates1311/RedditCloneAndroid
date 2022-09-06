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

public class PostsFragment extends Fragment {

    private final HttpClient httpClient = new HttpClient();
    private View view = null;
    private final String sortBy;
    List<ReactionDTO> reactions = new ArrayList<>();

    public PostsFragment(String sortBy) {
        this.sortBy = sortBy;
    }

    public static PostsFragment newInstance(String sortBy) {

        return new PostsFragment(sortBy);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        httpClient.setContext(getContext());

        if(getActivity() != null) {
            ((MainActivity)getActivity()).setGroupMenuVisibility(true,
                    true);
        }


        getActivity().setTitle("Reddit Clone");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_homepage).setChecked(true);

        view = inflater.inflate(R.layout.fragment_posts, container, false);

        initializeRecyclerView();

        return view;
    }


    public void getPosts(RecyclerView recyclerView, View view) {

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getContext());

        if(preferences.getString("username", null) != null) {

            RetrofitRepository<List<ReactionDTO>> reactionDTORetrofitRepository = new RetrofitRepository<>();

            reactionDTORetrofitRepository.sendRequest(httpClient.routes.getReactionsByUsername(), view,
                    () -> {
                        reactions = reactionDTORetrofitRepository.getResponseData();
                    });
        }

        RetrofitRepository<List<PostResponse>> postRetrofitRepository =
                new RetrofitRepository<>();

        postRetrofitRepository.sendRequest(httpClient.routes.getAllPosts(sortBy), view, () -> {

            List<PostResponse> postResponse = postRetrofitRepository.getResponseData();

            recyclerView.setAdapter(new PostRecyclerAdapter(getActivity(), postResponse, reactions,
                    preferences.getString("username", null), view));

        });
    }

    private void initializeRecyclerView() {

        if(getActivity() != null)
            ((MainActivity)getActivity()).setSortByMode("posts");

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPosts(recyclerView, view);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        //Toast.makeText(getActivity(), "onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ///Toast.makeText(getActivity(), "onDetach()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {

        super.onResume();

        initializeRecyclerView();




    }

}