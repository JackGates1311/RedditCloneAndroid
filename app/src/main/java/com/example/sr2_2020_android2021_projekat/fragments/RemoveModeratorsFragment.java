package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.R;

public class RemoveModeratorsFragment extends Fragment {

    public static RemoveModeratorsFragment newInsaance() {

        return new RemoveModeratorsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_remove_moderators, container, false);
    }
}
