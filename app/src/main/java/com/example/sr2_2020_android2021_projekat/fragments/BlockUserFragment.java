package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.R;

public class BlockUserFragment extends Fragment {

    public static BlockUserFragment newInstance() {

        return new BlockUserFragment();
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_block_user, container,
                false);

        return view;
    }
}
