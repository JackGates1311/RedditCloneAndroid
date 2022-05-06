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

public class CreatePostFragment extends Fragment {

    public static CreatePostFragment newInstance() {

        return new CreatePostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Create new post");

        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        return view;

    }
}