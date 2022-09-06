package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.adapters.FlairButtonRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;

import java.util.ArrayList;
import java.util.List;


public class CommunityFragment extends Fragment {

    private final RetrofitRepository<CommunityDTOResponse> retrofitRepository = new RetrofitRepository<>();

    private final HttpClient httpClient = new HttpClient();

    private final String communityNameParam;

    private TextView communityDescription;

    private View view;

    private RecyclerView recyclerView;

    public static CommunityFragment newInstance(String communityNameParam) {

        return new CommunityFragment(communityNameParam);
    }

    public CommunityFragment(String communityNameParam) {

        this.communityNameParam = communityNameParam;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if(getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(
                    false, false);

        getActivity().setTitle("Community details");

        view = inflater.inflate(R.layout.fragment_community, container, false);

        TextView communityName = view.findViewById(R.id.community_info_name);

        communityDescription = view.findViewById(R.id.community_info_description);

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getContext());

        ImageButton editCommunityButton = view.findViewById(R.id.button_edit_community);

        if(preferences.getString("authToken", null) != null) {

            editCommunityButton.setVisibility(View.VISIBLE);
        }

        editCommunityButton.setOnClickListener(v -> {

            ((MainActivity)getActivity()).setCommunityMode("EDIT");

            FragmentTransition.to(CreateEditCommunityFragment.newInstance(communityNameParam), getActivity(), true,
                    R.id.viewPage);
        });

        Button showAllCommunityPostsButton = view.findViewById(R.id.show_community_posts_button);

        showAllCommunityPostsButton.setOnClickListener(v ->
                FragmentTransition.to(CommunityPostsFragment.newInstance(
                        communityNameParam, "hot"), getActivity(), true,
                        R.id.viewPage));

        communityName.setText(communityNameParam);

        getCommunityByName();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getCommunityByName();

        view.findViewById(R.id.no_flairs_text).setVisibility(View.GONE);
        recyclerView.setAdapter(new FlairButtonRecyclerAdapter(getActivity(), new ArrayList<>()));
    }

    private void getCommunityByName() {

        retrofitRepository.sendRequest(httpClient.routes.getCommunityByName(communityNameParam),
                view, () -> {

            CommunityDTOResponse communityDTOResponse = retrofitRepository.getResponseData();

            communityDescription.setText(communityDTOResponse.getDescription());

            initializeRecyclerView(communityDTOResponse.getFlairs());

        });
    }

    private void initializeRecyclerView(List<String> flairs) {

        recyclerView = view.findViewById(R.id.recycler_view_flairs);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        assert flairs != null;
        if(!flairs.isEmpty())
            recyclerView.setAdapter(new FlairButtonRecyclerAdapter(getActivity(), flairs));
        else
            view.findViewById(R.id.no_flairs_text).setVisibility(View.VISIBLE);

    }


}