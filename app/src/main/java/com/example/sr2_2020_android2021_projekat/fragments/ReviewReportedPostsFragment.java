package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.card.MaterialCardView;

public class ReviewReportedPostsFragment extends Fragment {

    public static ReviewReportedPostsFragment newInstance() {

        return new ReviewReportedPostsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Reported posts");

        View view = inflater.inflate(R.layout.fragment_review_reported_posts, container, false);

        MaterialCardView reportedPostCard = (MaterialCardView) view.findViewById(R.id.reported_post_card);

        reportedPostCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), "onClick()", Toast.LENGTH_SHORT).show();

                FragmentTransition.to(ReportedPostDetailsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        return view;
    }
}