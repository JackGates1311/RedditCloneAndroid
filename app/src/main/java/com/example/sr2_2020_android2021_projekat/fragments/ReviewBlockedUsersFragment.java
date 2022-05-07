package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ReviewBlockedUsersFragment extends Fragment {

    public static ReviewBlockedUsersFragment newInstance() {

        return new ReviewBlockedUsersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Review blocked users");

        View view = inflater.inflate(R.layout.fragment_review_blocked_users, container,
                false);

        ImageView blockUserButton = (ImageView) view.findViewById(R.id.block_user_button);

        blockUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Block user")
                        .setMessage("Please select what do you want to block for selected user:")
                        .setView(R.layout.fragment_block_user)
                        .setPositiveButton("Block user", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });

        ImageView manageBlockedUserButton = (ImageView) view.findViewById(R.id.manage_blocked_user_button);

        manageBlockedUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Manage user permissions")
                        .setMessage("Please select what do you want to block for selected user:")
                        .setView(R.layout.fragment_block_user)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }

        });

        MaterialCardView reportedUserDetails = (MaterialCardView) view.findViewById(R.id.reported_user_card);

        reportedUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransition.to(ReportedUserDetailsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }

        });

        return view;
    }
}