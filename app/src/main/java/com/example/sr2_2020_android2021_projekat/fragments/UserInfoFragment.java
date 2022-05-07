package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.DialogInterface;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class UserInfoFragment extends Fragment {

    public static UserInfoFragment newInstance() {

        return new UserInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("User info");

        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        Button reportUserButton = (Button) view.findViewById(R.id.report_user_button);

        reportUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Report user")
                        .setMessage("Please select at least one reason for reporting user and " +
                                "describe the reason if you want:")
                        .setView(R.layout.fragment_report_user)
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

        return view;
    }
}