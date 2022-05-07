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

public class ReportedPostDetailsFragment extends Fragment {

    public static ReportedPostDetailsFragment newInstance() {

        return new ReportedPostDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reported_post_details, container,
                false);

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Reported post details");

        return view;

    }
}