package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sr2_2020_android2021_projekat.R;

public class ReportPostFragment extends Fragment {


    public ReportPostFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_post, container, false);
    }
}