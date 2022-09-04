package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PostDetailsFragment extends Fragment {

    private final RetrofitRepository<PostResponse> retrofitRepository = new RetrofitRepository<>();

    private final HttpClient httpClient = new HttpClient();

    private final Long postIdParam;

    private String communityName = null;

    public PostDetailsFragment(Long postIdParam) {
        this.postIdParam = postIdParam;
    }

    public static PostDetailsFragment newInstance(Long postIdParam) {

        return new PostDetailsFragment(postIdParam);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if(getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(true,
                false);

        getActivity().setTitle("Post details");

        View view = inflater.inflate(R.layout.fragment_post_details, container, false);

        LinearLayout cardTitle = view.findViewById(R.id.cardTitle);

        cardTitle.setOnClickListener(v -> {

            FragmentTransition.to(CommunityFragment.newInstance(communityName), getActivity(),
                    true, R.id.viewPage);

        });

        ImageButton reportPostButton = (ImageButton) view.findViewById(R.id.reportPostButton);

        reportPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), "onClick()", Toast.LENGTH_SHORT).show();

                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Report post")
                        .setMessage("Please select at least one reason for reporting post:")
                        .setView(R.layout.fragment_report_post)
                        .setPositiveButton("Report post", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        });

        ImageButton reportCommentButton = (ImageButton) view.findViewById(R.id.reportCommentButton);

        reportCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(view.getContext(), "onClick()", Toast.LENGTH_SHORT).show();

                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Report comment")
                        .setMessage("Please select at least one reason for reporting comment:")
                        .setView(R.layout.fragment_report_comment)
                        .setPositiveButton("Report", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });

        ImageButton editPostButton = (ImageButton) view.findViewById(R.id.editPostButton);

        editPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).setPostMode("EDIT");

                FragmentTransition.to(CreateEditPostFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        TextView displayNameComment = (TextView) view.findViewById(R.id.display_name_comment);

        displayNameComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransition.to(UserInfoFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });

        getPostById(view);

        return view;
    }

    private void getPostById(View view) {

        retrofitRepository.sendRequest(httpClient.routes.getPostById(postIdParam),
                view, () -> {

                    PostResponse postResponse = retrofitRepository.getResponseData();

                    communityName = postResponse.getCommunityName();

                    //communityDescription.setText(communityDTOResponse.getDescription());

                });
    }

}