package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;

public class PostsFragment extends Fragment {

    public static PostsFragment newInstance() {

        return new PostsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(true,
                true);

        getActivity().setTitle("Reddit Clone");

        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        CardView cardView = (CardView) view.findViewById(R.id.postCard);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransition.to(PostDetailsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //Toast.makeText(getActivity(), "onAttach()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        //Toast.makeText(getActivity(), "onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ///Toast.makeText(getActivity(), "onDeatach()", Toast.LENGTH_SHORT).show();
    }

}