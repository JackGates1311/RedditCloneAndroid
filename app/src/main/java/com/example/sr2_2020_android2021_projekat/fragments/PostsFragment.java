package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.adapters.PostRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.JsonPlaceholderAPI;
import com.example.sr2_2020_android2021_projekat.model.Post;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsFragment extends Fragment {

    ///

    private com.google.android.material.card.MaterialCardView postView;

    private TextView postCommunity;

    private TextView postTitle;

    private TextView postText;

    List<Post> posts;

    ///

    public static PostsFragment newInstance() {

        return new PostsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

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

        getPosts(recyclerView);





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

    public void getPosts(RecyclerView recyclerView) {

        //com.google.android.material.card.MaterialCardView postView;

        //TextView postCommunity = null;

        //TextView postTitle = null;

        //TextView postText = null;


        ///

        ///



        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/posts/").
                addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<Post>> call = jsonPlaceholderAPI.getAllPosts();

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()) {

                    // Errors 400 and 500

                    Toast.makeText(getContext(), "Code: HTTP " + response.code() + " error",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                recyclerView.setAdapter(new PostRecyclerAdapter(response.body()));

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                // network error (100 status)

                // postTitle.setText(t.getMessage());

            }
        });

        return;
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


    private List<Post> getPosts() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8080/api/posts/").
                addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<Post>> call = jsonPlaceholderAPI.getAllPosts();

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()) {

                    // Errors 400 and 500

                    Toast.makeText(getActivity(), "Code: HTTP " + response.code() + " error",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                posts = response.body();

                assert posts != null;

                for(Post post : posts) {

                    postCommunity.setText(post.getCommunityName());
                    postTitle.setText(post.getTitle());
                    postText.setText(post.getText());

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                // network error (100 status)

                postTitle.setText(t.getMessage());

            }
        });

        return posts;
    }

}