package com.example.sr2_2020_android2021_projekat.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.adapters.FlairCheckboxRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.FileResponse;
import com.example.sr2_2020_android2021_projekat.model.FlairDTO;
import com.example.sr2_2020_android2021_projekat.model.PostRequest;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.tools.DialogHelper;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MultipartBody;

public class CreateEditPostFragment extends Fragment {

    private final HttpClient httpClient = new HttpClient();

    private TextInputEditText title;
    private TextInputEditText text;
    private AutoCompleteTextView postCommunities;

    private DialogHelper dialogHelper = new DialogHelper();

    private PostResponse post;

    private List<String> postFlairs = new ArrayList<>();
    private List<String> selectedPostFlairs = new ArrayList<>();

    private List<MultipartBody.Part> multipartFiles = new ArrayList<>();

    private int fileCount = 0;

    private View view;
    private Long postId;

    public CreateEditPostFragment(PostResponse postResponse) {
        this.post = postResponse;
    }

    public static CreateEditPostFragment newInstance(PostResponse postResponse) {
        return new CreateEditPostFragment(postResponse);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_edit_post, container,
                false);

        httpClient.setContext(getContext());

        if(getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        title = view.findViewById(R.id.title);
        text = view.findViewById(R.id.text);

        postCommunities = view.findViewById(R.id.post_communities);

        Button createPostButton = view.findViewById(R.id.button_create_post);

        getAllCommunities(view);

        createPostButton.setOnClickListener(viewForm -> {

            if (validatePostData()) return;
            createPost(view);

        });

        MaterialButton saveChangesButton = view.findViewById(R.id.button_save_changes_post);

        saveChangesButton.setOnClickListener(v -> {
            if (validatePostData()) return;
            savePost(view);
        });

        MaterialButton selectFlairsButton = view.findViewById(R.id.post_select_flairs_button);

        selectFlairsButton.setOnClickListener(v -> {

            if(!postCommunities.getText().toString().equals("")) {
                dialogHelper.showDialog(getContext(), "Select flairs:",
                        R.layout.fragment_manage_flairs, () ->
                                postFlairs = selectedPostFlairs, () -> {},
                        this::getFlairs);
            } else {
                Toast.makeText(getContext(), "Please select community first",
                        Toast.LENGTH_SHORT).show();
            }

        });

        MaterialButton uploadImagesButton = view.findViewById(R.id.post_upload_images_button);

        uploadImagesButton.setOnClickListener(v -> {

            ((MainActivity)getActivity()).openFileChooser();

            if(((MainActivity) getActivity()).getMultipartFile() != null) {
                multipartFiles.add(((MainActivity) getActivity()).getMultipartFile());
            }

        });

        if(Objects.equals(((MainActivity) getActivity()).getPostMode(), "ADD")) {

            getActivity().setTitle("Create new post");

            ((MainActivity) getActivity()).menu.setGroupVisible(R.id.addGroup, false);

            view.findViewById(R.id.button_create_post).setVisibility(View.VISIBLE);

            view.findViewById(R.id.button_save_changes_post).setVisibility(View.GONE);

        } else {

            getPost();

            getActivity().setTitle("Edit post");
            view.findViewById(R.id.post_communities).setEnabled(false);
            createPostButton.setVisibility(View.GONE);
            saveChangesButton.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void savePost(View view) {

        RetrofitRepository<PostRequest> retrofitRepository = new RetrofitRepository<>();

        PostRequest postRequest = new PostRequest(
                Objects.requireNonNull(postCommunities.getText()).toString(),
                Objects.requireNonNull(text.getText()).toString(),
                Objects.requireNonNull(title.getText()).toString(),
                selectedPostFlairs);

        retrofitRepository.sendRequest(httpClient.routes.editPost(post.getPostId(),
                postRequest), view, () -> Toast.makeText(getContext(), "Post changes are successfully saved",
                        Toast.LENGTH_LONG).show());

        post.setTitle(postRequest.getTitle());
        post.setText(postRequest.getText());
        post.setFlairs(selectedPostFlairs);

        if(getActivity() != null)
            FragmentTransition.to(PostDetailsFragment.newInstance(post), getActivity(),
                    false, R.id.viewPage);
    }

    private void uploadPostImages(View view, Long postId) {

        RetrofitRepository<FileResponse> retrofitRepository = new RetrofitRepository<>();

        if(getActivity() != null)

            //todo enable multi image upload

           //for(MultipartBody.Part multipartFile: multipartFiles) {
                retrofitRepository.sendRequest(httpClient.routes.uploadFile(postId,
                        ((MainActivity)getActivity()).getMultipartFile()), view, () -> {
                    Toast.makeText(getContext(), "Post image are successfully uploaded",
                            Toast.LENGTH_LONG).show();
                });
          // }

    }

    private boolean validatePostData() {
        boolean isCommunityValid = postCommunities.getText().toString().length() > 0;
        boolean isTitleValid = Objects.requireNonNull(title.getText()).toString().length() > 0;
        boolean isTextValid = Objects.requireNonNull(text.getText()).toString().length() > 0;

        if(!isCommunityValid) {

            Toast.makeText(getContext(), "Please select a community",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        if(!isTitleValid) {

            Toast.makeText(getContext(), "Please provide a valid post title",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        if(!isTextValid) {

            Toast.makeText(getContext(), "Please provide a valid post text",
                    Toast.LENGTH_SHORT).show();

            return true;
        }
        return false;
    }

    private void getFlairs() {
        RetrofitRepository<CommunityDTOResponse> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.getCommunityByName(
                postCommunities.getText().toString()), view, () -> {

                initializeRecyclerView(retrofitRepository.getResponseData().getFlairs());

        });
    }

    private void initializeRecyclerView(List<String> flairs) {

        Dialog dialog = dialogHelper.getCurrentDialog();

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler_view_flairs);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        assert flairs != null;
        if(!flairs.isEmpty())
            recyclerView.setAdapter(new FlairCheckboxRecyclerAdapter(null,
                    this, getActivity(), flairs));
        else {
            Toast.makeText(getContext(), "No flairs available for selected community",
                    Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }

    private void getPost() {

        selectedPostFlairs = post.getFlairs();
        postCommunities.setText(post.getCommunityName());
        title.setText(post.getTitle());
        text.setText(post.getText());
    }

    private void getAllCommunities(View view) {

        RetrofitRepository<List<CommunityDTOResponse>> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.getAllCommunities(), view, () -> {

            List<String> communityNames = new ArrayList<>();

            assert retrofitRepository.getResponseData() != null;

            for(CommunityDTOResponse communityDTOResponse : retrofitRepository.getResponseData()) {

                communityNames.add(communityDTOResponse.getName());
            }

            ArrayAdapter<String> adapter = new
                    ArrayAdapter<>(getContext(), R.layout.drop_down_item, communityNames);

            AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.post_communities);

            autoCompleteTextView.setAdapter(adapter);

        });
    }

    private void createPost(View view) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        PostRequest postRequest = new PostRequest(postCommunities.getText().toString(),
                Objects.requireNonNull(text.getText()).toString(),
                Objects.requireNonNull(title.getText()).toString(), selectedPostFlairs);

        retrofitRepository.sendRequest(httpClient.routes.createPost(postRequest), view, () -> {
                    Toast.makeText(getContext(), "Post successfully created",
                            Toast.LENGTH_LONG).show();
                    getNewPostId(view);

                });


        if(getActivity() != null)
            FragmentTransition.to(PostsFragment.newInstance("hot"), getActivity(),
                    false, R.id.viewPage);

    }

    private void getNewPostId(View view) {

        RetrofitRepository<List<PostResponse>> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.getAllPosts("new"), view, () -> {

            for(PostResponse postResponse : retrofitRepository.getResponseData()) {

                if(Objects.equals(String.valueOf(text.getText()), postResponse.getText()) &&
                    Objects.equals(String.valueOf(title.getText()), postResponse.getTitle()) &&
                    Objects.equals(String.valueOf(postCommunities.getText()),
                    postResponse.getCommunityName()) && Objects.equals(selectedPostFlairs,
                        postResponse.getFlairs())) {

                    postId = postResponse.getPostId();

                    uploadPostImages(view, postId);

                    break;
                }

            }

        });
    }

    public List<String> getPostFlairs() {
        return postFlairs;
    }

    public void setPostFlairs(List<String> postFlairs) {
        this.postFlairs = postFlairs;
    }

    public List<String> getSelectedPostFlairs() {
        return selectedPostFlairs;
    }

    public void setSelectedPostFlairs(List<String> selectedPostFlairs) {
        this.selectedPostFlairs = selectedPostFlairs;
    }
}