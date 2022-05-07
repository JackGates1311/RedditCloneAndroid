package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.google.android.material.navigation.NavigationView;

public class LoginFragment extends Fragment {

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup vg,
                             @Nullable Bundle data) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Login to Reddit Clone");

        View view = inflater.inflate(R.layout.fragment_login, vg, false);

        Button loginButton = (Button) view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(view.getContext(), "onClick()", Toast.LENGTH_SHORT).show();

                ((MainActivity) getActivity()).navigationView.getMenu().
                        findItem(R.id.navigation_bar_item_user_register).setVisible(false);

                ((MainActivity) getActivity()).navigationView.getMenu().
                        findItem(R.id.navigation_bar_item_user_login).setVisible(false);

                ((MainActivity) getActivity()).navigationView.getMenu().
                        findItem(R.id.navigation_bar_item_user_logout).setVisible(true);

                ((MainActivity) getActivity()).navigationView.getMenu().
                        findItem(R.id.navigation_bar_item_user_manage).setVisible(true);

                ((MainActivity) getActivity()).navigationView.getMenu().
                        findItem(R.id.navigation_bar_item_create_community).setVisible(true);

                ((MainActivity) getActivity()).navigationView.getMenu().
                        findItem(R.id.navigation_bar_item_communities).setVisible(true);

                ((MainActivity) getActivity()).navigationView.getMenu().
                        findItem(R.id.navigation_bar_item_administrator_tools).setVisible(true);


                FragmentTransition.to(PostsFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);




            }
        });


        //Toast.makeText(getActivity(), "onAttach()", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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
        //Toast.makeText(getActivity(), "onDeatach()", Toast.LENGTH_SHORT).show();
    }



}
