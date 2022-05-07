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

public class ReviewReportedCommentsFragment extends Fragment {

    public static ReviewReportedCommentsFragment newInstance() {

        return new ReviewReportedCommentsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Reported comments");

        View view = inflater.inflate(R.layout.fragment_review_reported_comments, container,
                false);

        MaterialCardView reportedCommentCard =
                (MaterialCardView) view.findViewById(R.id.reported_comment_card);

        reportedCommentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(view.getContext(), "onClick()", Toast.LENGTH_SHORT).show();

                FragmentTransition.to(ReportedCommentDetailsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);


            }
        });



        Button acceptReportsOnSelectedComments = (Button) view.findViewById(R.id.accept_reported_comments_button);

        acceptReportsOnSelectedComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO

            }
        });

        Button declineReportsOnSelectedComments = (Button) view.findViewById(R.id.decline_reported_comments_button);

        declineReportsOnSelectedComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO

            }
        });



        return view;
    }
}