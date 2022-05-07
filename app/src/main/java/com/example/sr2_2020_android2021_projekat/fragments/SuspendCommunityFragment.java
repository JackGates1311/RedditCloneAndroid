package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.R;

public class SuspendCommunityFragment extends Fragment {

    public static SuspendCommunityFragment newInstance(){

        return new SuspendCommunityFragment();
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suspend_community, container, false);
    }

}
