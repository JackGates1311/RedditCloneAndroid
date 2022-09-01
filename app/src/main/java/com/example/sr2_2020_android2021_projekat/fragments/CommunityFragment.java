package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;


public class CommunityFragment extends Fragment {

    private final RetrofitRepository<CommunityDTOResponse> retrofitRepository = new RetrofitRepository<>();

    private final HttpClient httpClient = new HttpClient();

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

        if(getActivity() != null)
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

        retrofitRepository.sendRequest(httpClient.routes.getCommunityByName(communityNameParam),
                view, () -> {

            CommunityDTOResponse communityDTOResponse = retrofitRepository.getResponseData();

            communityDescription.setText(communityDTOResponse.getDescription());

        });

    }
}