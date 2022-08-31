package com.example.sr2_2020_android2021_projekat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.CrudService;
import com.example.sr2_2020_android2021_projekat.model.Community;
import com.example.sr2_2020_android2021_projekat.model.PostRequest;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateEditPostFragment extends Fragment {

    private final HttpClient httpClient = new HttpClient();

    private com.google.android.material.textfield.TextInputEditText title;
    private com.google.android.material.textfield.TextInputEditText text;

    private AutoCompleteTextView postCommunities;

    public static CreateEditPostFragment newInstance() {
        return new CreateEditPostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_edit_post, container,
                false);

        if(getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        title = view.findViewById(R.id.title);
        text = view.findViewById(R.id.text);

        postCommunities = view.findViewById(R.id.postCommunities);

        Button createPostButton = view.findViewById(R.id.button_create_post);

        getAllCommunities(view);

        createPostButton.setOnClickListener(viewForm -> {

            boolean isCommunityValid = postCommunities.getText().toString().length() > 0;
            boolean isTitleValid = Objects.requireNonNull(title.getText()).toString().length() > 0;
            boolean isTextValid = Objects.requireNonNull(text.getText()).toString().length() > 0;

            if(!isCommunityValid) {

                Toast.makeText(getContext(), "Please select a community",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            if(!isTitleValid) {

                Toast.makeText(getContext(), "Please provide a valid post title",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            if(!isTextValid) {

                Toast.makeText(getContext(), "Please provide a valid post text",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            createPost(view);

        });

        httpClient.setContext(getContext());

        if(Objects.equals(((MainActivity) getActivity()).postMode, "ADD")) {

            getActivity().setTitle("Create new post");

            view.findViewById(R.id.button_create_post).setVisibility(View.VISIBLE);

            view.findViewById(R.id.button_save_changes_post).setVisibility(View.GONE);

        } else {

            getActivity().setTitle("Edit post");

            view.findViewById(R.id.button_create_post).setVisibility(View.GONE);

            view.findViewById(R.id.button_save_changes_post).setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void getAllCommunities(View view) {

        CrudService<Community> crudService = new CrudService<>();

        crudService.getDataList(httpClient.routes.getAllCommunities(), view, () -> {

            List<String> communityNames = new ArrayList<>();

            assert crudService.getResponseDataList() != null;

            for(Community community : crudService.getResponseDataList()) {

                communityNames.add(community.getName());
            }

            ArrayAdapter<String> adapter = new
                    ArrayAdapter<>(getContext(), R.layout.drop_down_item, communityNames);

            AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.postCommunities);

            autoCompleteTextView.setAdapter(adapter);

        });
    }

    private void createPost(View view) {

        CrudService<String> crudService = new CrudService<>();

        PostRequest postRequest = new PostRequest(postCommunities.getText().toString(),
                Objects.requireNonNull(text.getText()).toString(),
                Objects.requireNonNull(title.getText()).toString(), null);

        crudService.postData(httpClient.routes.createPost(postRequest), view, () ->
                Toast.makeText(getContext(), "Post successfully created",
                Toast.LENGTH_LONG).show());

        if(getActivity() != null)
            FragmentTransition.to(PostsFragment.newInstance(), getActivity(),
                    false, R.id.viewPage);

    }
}