package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;

public class PostDetailsFragment extends Fragment {

    public static PostDetailsFragment newInstance() {

        return new PostDetailsFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setSortGroupMenuVisibility(true,
                false);

        getActivity().setTitle("Post details");

        View view = inflater.inflate(R.layout.fragment_post_details, container, false);

        //

        LinearLayout cardTitle = (LinearLayout) view.findViewById(R.id.cardTitle);

        cardTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(view.getContext(), "onClick()", Toast.LENGTH_SHORT).show();

                FragmentTransition.to(CommunityFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        //

        return view;
    }

}