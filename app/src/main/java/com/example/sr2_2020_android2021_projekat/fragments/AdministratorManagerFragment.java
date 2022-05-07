package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AdministratorManagerFragment extends Fragment {

    public static AdministratorManagerFragment newInstance() {

        return new AdministratorManagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Administrator manager");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_administrator_tools).setChecked(true);

        View view = inflater.inflate(R.layout.fragment_administrator_manager, container,
                false);

        ImageView suspendCommunityButton = (ImageView) view.findViewById(R.id.suspend_community_button);

        suspendCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Suspend community")
                        .setMessage("Please select at least one reason for suspending community " +
                                "and describe the reason if you want:")
                        .setView(R.layout.fragment_suspend_community)
                        .setPositiveButton("Report", new DialogInterface.OnClickListener() {
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

        ImageView removeModeratorButton = (ImageView)
                view.findViewById(R.id.remove_moderator_button);

        removeModeratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Remove moderators")
                        .setMessage("Please select moderators which you want to suspend from community:")
                        .setView(R.layout.fragment_suspend_community)
                        .setPositiveButton("Suspend", new DialogInterface.OnClickListener() {
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

        return view;
    }
}