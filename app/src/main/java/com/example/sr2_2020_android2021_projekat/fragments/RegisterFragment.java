package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;

public class RegisterFragment extends Fragment {


    public static RegisterFragment newInstance() {

        return new RegisterFragment();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Register to Reddit Clone");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_register).setChecked(true);

        return inflater.inflate(R.layout.fragment_register, vg, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
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
