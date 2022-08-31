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
import com.example.sr2_2020_android2021_projekat.adapters.PostRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.CrudService;
import com.example.sr2_2020_android2021_projekat.api.Routes;
import com.example.sr2_2020_android2021_projekat.model.Community;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.EnvironmentConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityFragment extends Fragment {

    private final CrudService<Community> crudService = new CrudService<>();

    private final String communityNameParam;

    private TextView communityDescription;

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

        if((MainActivity)getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(
                    false, false);

        getActivity().setTitle("Community details");

        View view = inflater.inflate(R.layout.fragment_community, container, false);

        TextView communityName = view.findViewById(R.id.community_info_name);

        communityDescription = view.findViewById(R.id.community_info_description);

        Button showAllCommunityPostsButton = view.findViewById(R.id.show_community_posts_button);

        showAllCommunityPostsButton.setOnClickListener(view1 ->
                FragmentTransition.to(CommunityPostsFragment.newInstance(
                        communityNameParam), getActivity(), true, R.id.viewPage));

        communityName.setText(communityNameParam);

        getCommunityByName(view);

        return view;
    }

    private void getCommunityByName(View view) {

        //TODO make develop branch and do commit
        //Add interceptor
        //Refactor code

        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(EnvironmentConfig.baseURL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Routes route = retrofit.create(Routes.class);

        Call<Community> call = route.getCommunityByName(communityNameParam);

        call.enqueue(new Callback<Community>() {

            @Override
            public void onResponse(@NonNull Call<Community> call,
                                   @NonNull Response<Community> response) {

                if(!response.isSuccessful()) {

                    // Errors 400 and 500

                    Toast.makeText(getContext(), "HTTP returned code " + response.code(),
                            Toast.LENGTH_LONG).show();

                    return;
                }

                Community community = response.body();

                assert community != null;
                communityDescription.setText(community.getDescription());
            }

            @Override
            public void onFailure(@NonNull Call<Community> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Connection with server is not established", Toast.LENGTH_LONG).show();
            }
        });*/

        crudService.getData(EnvironmentConfig.routes.getCommunityByName(communityNameParam), view, () -> {

            Community community  = crudService.getResponseData().get(0);

            communityDescription.setText(community.getDescription());

        });

    }
}