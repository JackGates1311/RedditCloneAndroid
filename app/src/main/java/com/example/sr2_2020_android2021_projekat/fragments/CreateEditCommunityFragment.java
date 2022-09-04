package com.example.sr2_2020_android2021_projekat.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.adapters.FlairCheckboxRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTORequest;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.FlairDTO;
import com.example.sr2_2020_android2021_projekat.tools.DialogHelper;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateEditCommunityFragment extends Fragment {

    HttpClient httpClient = new HttpClient();

    private Button selectFlairsButton;
    private View view;
    private List<String> selectedCommunityFlairs = new ArrayList<>();
    private DialogHelper dialogHelper = new DialogHelper();

    private com.google.android.material.textfield.TextInputEditText communityName;
    private com.google.android.material.textfield.TextInputEditText communityDescription;
    private List<String> communityFlairs = new ArrayList<>();

    private String communityNameParam;

    private long communityId;

    public CreateEditCommunityFragment(String communityNameParam) {
        this.communityNameParam = communityNameParam;
    }

    public static CreateEditCommunityFragment newInstance(String communityNameParam) {

        return new CreateEditCommunityFragment(communityNameParam);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_edit_community, container, false);

        httpClient.setContext(getContext());

        if(getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        communityName = view.findViewById(R.id.community_name);
        communityDescription = view.findViewById(R.id.community_description);

        MaterialButton createCommunityButton = view.findViewById(R.id.button_create_community);

        createCommunityButton.setOnClickListener(v -> {
            validateCommunityData();
            createCommunity(view);
        });

        MaterialButton saveCommunityButton = view.findViewById(R.id.button_save_community);

        if(((MainActivity) getActivity()).getCommunityMode().equals("ADD")) {

            getActivity().setTitle("Create community");
            createCommunityButton.setVisibility(View.VISIBLE);
            saveCommunityButton.setVisibility(View.GONE);
            ((MainActivity)getActivity()).navigationView.getMenu().
                    findItem(R.id.navigation_bar_item_create_community).setChecked(true);


        } else {

            getCommunityByName(communityNameParam);

            view.findViewById(R.id.community_name).setEnabled(false);
            createCommunityButton.setVisibility(View.GONE);
            saveCommunityButton.setVisibility(View.VISIBLE);
            getActivity().setTitle("Edit community");

        }

        selectFlairsButton = view.findViewById(R.id.button_select_flairs);

        selectFlairsButton.setOnClickListener(view -> {

            dialogHelper.showDialog(getContext(), "Select flairs:",
                    R.layout.fragment_manage_flairs, () ->
                            communityFlairs = selectedCommunityFlairs, () -> {},
                    this::getFlairs, "Add flair",
                    this::showAddFlairDialog);

        });

        saveCommunityButton.setOnClickListener(v -> {

            validateCommunityData();
            saveCommunityChanges(view);

        });


        return view;
    }

    private void getCommunityByName(String communityNameParam) {

        RetrofitRepository<CommunityDTOResponse> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.getCommunityByName(communityNameParam),
                view, () -> {

                    CommunityDTOResponse communityDTOResponse = retrofitRepository.getResponseData();

                    selectedCommunityFlairs = communityDTOResponse.getFlairs();
                    communityId = communityDTOResponse.getCommunityId();
                    communityName.setText(communityDTOResponse.getName());
                    communityDescription.setText(communityDTOResponse.getDescription());

                });
    }

    private void saveCommunityChanges(View view) {

        RetrofitRepository<CommunityDTORequest> retrofitRepository = new RetrofitRepository<>();

        CommunityDTORequest communityDTORequest = new CommunityDTORequest(
                Objects.requireNonNull(communityName.getText()).toString(),
                Objects.requireNonNull(communityDescription.getText()).toString(),
                selectedCommunityFlairs);

        Log.d("communityDTORequest", String.valueOf(communityDTORequest));

        retrofitRepository.sendRequest(httpClient.routes.editCommunity(communityId,
                        communityDTORequest), view, () ->
                Toast.makeText(getContext(), "Community changes are successfully saved",
                        Toast.LENGTH_LONG).show());

        if(getActivity() != null)
            FragmentTransition.to(CommunityFragment.newInstance(communityNameParam), getActivity(),
                    false, R.id.viewPage);
    }

    private void validateCommunityData() {
        boolean isCommunityNameValid = Objects.requireNonNull(
                communityName.getText()).toString().length() > 0;
        boolean isCommunityDescriptionValid = Objects.requireNonNull(
                communityDescription.getText()).toString().length() > 0;

        if(!isCommunityNameValid) {

            Toast.makeText(getContext(), "Please provide a valid community name",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isCommunityDescriptionValid) {
            Toast.makeText(getContext(), "Please provide a valid community description",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void showAddFlairDialog() {

        dialogHelper.showDialog(getContext(), "Add flair:", R.layout.fragment_add_flair,
                () -> {
                        TextInputEditText flairName =
                                dialogHelper.getCurrentDialog().findViewById(R.id.flair_name);
                        addFlair(view, Objects.requireNonNull(flairName.getText()).toString());
                }, () -> {

                }, () -> {

                });
    }

    private void createCommunity(View view) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        CommunityDTORequest communityDTORequest = new CommunityDTORequest(
                Objects.requireNonNull(communityName.getText()).toString(),
                Objects.requireNonNull(communityDescription.getText()).toString(),
                communityFlairs);

        retrofitRepository.sendRequest(httpClient.routes.createCommunity(communityDTORequest),
                view, () -> Toast.makeText(getContext(), "Community successfully created",
                        Toast.LENGTH_LONG).show());

        if(getActivity() != null)
            FragmentTransition.to(PostsFragment.newInstance("hot"), getActivity(),
                    false, R.id.viewPage);
    }

    private void getFlairs() {

        RetrofitRepository<List<FlairDTO>> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.getAllFlairs(), view, () -> {

            List<FlairDTO> flairDTOResponse = retrofitRepository.getResponseData();

            List<String> flairNames = new ArrayList<>();

            for(FlairDTO flair: flairDTOResponse) {
                flairNames.add(flair.getName());
            }

            initializeRecyclerView(flairNames);

        });
    }

    private void initializeRecyclerView(List<String> flairNames) {

        Dialog dialog = dialogHelper.getCurrentDialog();

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler_view_flairs);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        assert flairNames != null;
        if(!flairNames.isEmpty())
            recyclerView.setAdapter(new FlairCheckboxRecyclerAdapter(this,
                    getActivity(), flairNames));

    }

    private void addFlair(View view, String flairName) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        FlairDTO flairDTO = new FlairDTO(flairName);

        retrofitRepository.sendRequest(httpClient.routes.addFlair(flairDTO), view, () -> {
            Toast.makeText(getContext(), "Flair successfully saved",
                    Toast.LENGTH_LONG).show();
        });

    }

    public List<String> getSelectedCommunityFlairs() {
        return selectedCommunityFlairs;
    }
}