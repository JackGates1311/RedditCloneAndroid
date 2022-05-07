package com.example.sr2_2020_android2021_projekat.fragments;

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

public class CreateEditCommunityFragment extends Fragment {

    public static CreateEditCommunityFragment newInstance() {

        return new CreateEditCommunityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_edit_community, container, false);

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        if(((MainActivity)getActivity()).communityMode == "ADD") {

            getActivity().setTitle("Create community");

            view.findViewById(R.id.button_create_community).setVisibility(View.VISIBLE);

            view.findViewById(R.id.button_save_changes_community).setVisibility(View.GONE);

            ((MainActivity)getActivity()).navigationView.getMenu().
                    findItem(R.id.navigation_bar_item_create_community).setChecked(true);


        } else {

            view.findViewById(R.id.button_create_community).setVisibility(View.GONE);

            view.findViewById(R.id.button_save_changes_community).setVisibility(View.VISIBLE);

            getActivity().setTitle("Edit community");
        }

        return view;
    }
}