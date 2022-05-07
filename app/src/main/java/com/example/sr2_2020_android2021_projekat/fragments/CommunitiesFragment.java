package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.card.MaterialCardView;

public class CommunitiesFragment extends Fragment {

    public static CommunitiesFragment newInstance() {

        return new CommunitiesFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Communities");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_communities).setChecked(true);

        View view = inflater.inflate(R.layout.fragment_communities, container, false);

        MaterialCardView communityCard = (MaterialCardView) view.findViewById(R.id.community_card);

        communityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).communityMode = "EDIT";

                FragmentTransition.to(CreateEditCommunityFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        Button reviewReportedCommentsButton =
                (Button) view.findViewById(R.id.review_reported_comments_button);

        reviewReportedCommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransition.to(ReviewReportedCommentsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        Button reviewReportedPostsButton =
                (Button) view.findViewById(R.id.review_reported_posts_button);

        reviewReportedPostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransition.to(ReviewReportedPostsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        Button reviewBlockedUsersButton =
                (Button) view.findViewById(R.id.review_blocked_users_button);

        reviewBlockedUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransition.to(ReviewBlockedUsersFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        return view;

    }
}