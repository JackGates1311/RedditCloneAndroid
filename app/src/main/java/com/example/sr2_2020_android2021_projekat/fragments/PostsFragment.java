package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.util.Log;
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
import com.example.sr2_2020_android2021_projekat.tools.EnvironmentConfig;
import com.example.sr2_2020_android2021_projekat.tools.RetrofitTools;

import java.util.List;
import java.util.function.Function;

public class PostsFragment extends Fragment {

    private final CrudService<PostResponse> crudService = new CrudService<>();

    ///

    /* private com.google.android.material.card.MaterialCardView postView;

    private TextView postCommunity;

    private TextView postTitle;

    private TextView postText; */

    List<PostResponse> postResponses;

    private final RetrofitTools retrofitTools = new RetrofitTools();

    //TODO you must instance object properly to avoid error ... on null reference object ...



    ///

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


        //CardView postView = (CardView) view.findViewById(R.id.post_card);

/*        postView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransition.to(PostDetailsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });*/

        /////


        ////

        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPosts(recyclerView, view);


        //recyclerView.addItemDecoration();

        /* postCommunity = (TextView) view.findViewById(R.id.post_community);

        postTitle = (TextView) view.findViewById(R.id.post_title);

        postText = (TextView) view.findViewById(R.id.post_text);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/posts/").
                addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<Post>> call = jsonPlaceholderAPI.getAllPosts();

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()) {

                    Toast.makeText(getActivity(), "Code: HTTP " + response.code() + " error",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                List<Post> posts = response.body();

                for(Post post : posts) {

                    postCommunity.setText(post.getCommunityName());
                    postTitle.setText(post.getTitle());
                    postText.setText(post.getText());

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                postTitle.setText(t.getMessage());

            }
        }); */

        return view;
    }

    public void getPosts(RecyclerView recyclerView, View view) {

        /*Call<List<PostResponse>> call = EnvironmentConfig.routes.getAllPosts();

        call.enqueue(new Callback<List<PostResponse>>() {

            @Override
            public void onResponse(@NonNull Call<List<PostResponse>> call,
                                   @NonNull Response<List<PostResponse>> response) {

                if(!response.isSuccessful()) {

                    RetrofitTools.showResponseErrorMessage(response.code(), view);

                    return;
                }

                postResponses = response.body();

                recyclerView.setAdapter(new PostRecyclerAdapter(getActivity(), response.body()));

            }

            @Override
            public void onFailure(@NonNull Call<List<PostResponse>> call, @NonNull Throwable t) {
                RetrofitTools.showConnectionErrorMessage(t, view);
            }
        });*/

        ;


        crudService.getData(EnvironmentConfig.routes.getAllPosts(), view, () -> {

            List<PostResponse> postResponse = crudService.getResponseData();

            recyclerView.setAdapter(new PostRecyclerAdapter(getActivity(), postResponse));

        });
    }

    private void doSomething() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

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
        ///Toast.makeText(getActivity(), "onDeatach()", Toast.LENGTH_SHORT).show();
    }


    /*private List<PostResponse> getPosts() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.0.75:8080/api/posts/").
                addConverterFactory(GsonConverterFactory.create()).build();

        Routes routes = retrofit.create(Routes.class);

        Call<List<PostResponse>> call = routes.getAllPosts();

        call.enqueue(new Callback<List<PostResponse>>() {

            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {

                if(!response.isSuccessful()) {

                    // Errors 400 and 500

                    Toast.makeText(getActivity(), "Code: HTTP " + response.code() + " error",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                postResponses = response.body();

                assert postResponses != null;

                for(PostResponse postResponse : postResponses) {

                    postCommunity.setText(postResponse.getCommunityName());
                    postTitle.setText(postResponse.getTitle());
                    postText.setText(postResponse.getText());

                }

            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {

                // network error (100 status)

                postTitle.setText(t.getMessage());

            }
        });

        return postResponses;
    }*/

}