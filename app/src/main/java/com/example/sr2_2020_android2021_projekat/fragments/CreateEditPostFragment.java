package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;

public class CreateEditPostFragment extends Fragment {

    public static CreateEditPostFragment newInstance() {

        return new CreateEditPostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_edit_post, container, false);

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        if(((MainActivity)getActivity()).postMode == "ADD") {

            getActivity().setTitle("Create new post");

            view.findViewById(R.id.button_create_post).setVisibility(View.VISIBLE);

            view.findViewById(R.id.button_save_changes_post).setVisibility(View.GONE);

        } else {

            getActivity().setTitle("Edit post");

            view.findViewById(R.id.button_create_post).setVisibility(View.GONE);

            view.findViewById(R.id.button_save_changes_post).setVisibility(View.VISIBLE);
        }

        return view;

    }
}