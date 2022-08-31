package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
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
import com.example.sr2_2020_android2021_projekat.api.CrudService;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;

import java.util.List;

public class PostsFragment extends Fragment {

    private final CrudService<PostResponse> crudService = new CrudService<>();
    private final HttpClient httpClient = new HttpClient();

    public static PostsFragment newInstance() {

        return new PostsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if((MainActivity)getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(true,
                true);

        getActivity().setTitle("Reddit Clone");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_homepage).setChecked(true);

        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPosts(recyclerView, view);

        return view;
    }

    public void getPosts(RecyclerView recyclerView, View view) {

        crudService.getDataList(httpClient.routes.getAllPosts(), view, () -> {

            List<PostResponse> postResponse = crudService.getResponseDataList();

            recyclerView.setAdapter(new PostRecyclerAdapter(getActivity(), postResponse));

        });
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //Toast.makeText(getActivity(), "onAttach()", Toast.LENGTH_SHORT).show();
    }*/

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

}