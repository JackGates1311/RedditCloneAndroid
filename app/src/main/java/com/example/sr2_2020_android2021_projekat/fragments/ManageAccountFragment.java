package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ManageAccountFragment extends Fragment {

    public static ManageAccountFragment newInstance() {

        return new ManageAccountFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Manage account");

        View view = inflater.inflate(R.layout.fragment_manage_account, container, false);

        Button changePasswordButton = (Button) view.findViewById(R.id.change_password_button);

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), "onClick()", Toast.LENGTH_SHORT).show();

                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Change account password")
                        .setView(R.layout.fragment_change_password)
                        .setPositiveButton("Change", new DialogInterface.OnClickListener() {
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