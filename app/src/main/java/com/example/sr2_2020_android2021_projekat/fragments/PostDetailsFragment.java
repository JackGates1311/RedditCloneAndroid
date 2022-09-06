package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.adapters.CommentRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.adapters.FlairButtonRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.adapters.PostRecyclerAdapter;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.CommentDTORequest;
import com.example.sr2_2020_android2021_projekat.model.CommentDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.model.ReactionDTO;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostDetailsFragment extends Fragment {

    private final RetrofitRepository<PostResponse> retrofitRepository = new RetrofitRepository<>();

    private final HttpClient httpClient = new HttpClient();

    private final PostResponse post;

    private String communityName = null;

    private View view;

    private List<ReactionDTO> reactions = new ArrayList<>();

    public SharedPreferences preferences;

    public PostDetailsFragment(PostResponse postResponse) {
        this.post = postResponse;
    }

    public static PostDetailsFragment newInstance(PostResponse postResponse) {

        return new PostDetailsFragment(postResponse);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        httpClient.setContext(getContext());

        preferences = PreferenceManager.
                getDefaultSharedPreferences(getContext());

        if(getActivity() != null)
            ((MainActivity)getActivity()).setGroupMenuVisibility(true,
                false);

        getActivity().setTitle("Post details");

        view = inflater.inflate(R.layout.fragment_post_details, container, false);

        TextInputEditText replyTextComment = view.findViewById(R.id.reply_text_comment);
        ImageButton postCommentButton = view.findViewById(R.id.post_comment_button);

        postCommentButton.setOnClickListener(v -> {

            RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

            CommentDTORequest commentDTORequest = new CommentDTORequest(
                    Objects.requireNonNull(replyTextComment.getText()).toString(), post.getPostId(), null);

            retrofitRepository.sendRequest(httpClient.routes.postComment(commentDTORequest), view,
                    () -> {
                        Toast.makeText(getActivity(), "Comment successfully posted",
                                Toast.LENGTH_SHORT).show();

                        FragmentTransition.to(PostDetailsFragment.newInstance(post), getActivity(),
                                false, R.id.viewPage);
                    });
        });

        /*ImageButton reportCommentButton = (ImageButton) view.findViewById(R.id.reportCommentButton);

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
        });*/

        /*TextView displayNameComment = (TextView) view.findViewById(R.id.display_name_comment);

        displayNameComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransition.to(UserInfoFragment.newInstance(), getActivity(),
                        true, R.id.viewPage);

            }
        });*/

        initializeCommentRecyclerView();

        initializeRecyclerView();

        return view;
    }

    private void initializeRecyclerView() {

        if(preferences.getString("authToken", null) != null) {
            getReactions();
        } else {
            view.findViewById(R.id.reply_text_comment).setVisibility(View.GONE);
            view.findViewById(R.id.post_comment_button).setVisibility(View.GONE);
            getPost();
        }

    }

    private void initializeCommentRecyclerView(){

        RecyclerView commentRecyclerView = view.findViewById(R.id.comments_recycler_view);

        commentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getCommentsForPost(commentRecyclerView, view);

    }

    private void getCommentsForPost(RecyclerView commentRecyclerView, View view) {

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getContext());

        RetrofitRepository<List<CommentDTOResponse>> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.getPostComments(
                post.getPostId(), "hot"), view, () -> {

            List<CommentDTOResponse> commentDTOResponses = retrofitRepository.getResponseData();

            commentRecyclerView.setAdapter(new CommentRecyclerAdapter(getActivity(), commentDTOResponses,
                    preferences.getString("username", null), view, post));

        });
    }

    private void getReactions() {

        RetrofitRepository<List<ReactionDTO>> reactionDTORetrofitRepository = new RetrofitRepository<>();

        reactionDTORetrofitRepository.sendRequest(httpClient.routes.getReactionsByUsername(), view,
                () -> {

                    reactions = reactionDTORetrofitRepository.getResponseData();

                    getPost();

                });
    }

    private void getPost() {
        if(getActivity() != null)
            ((MainActivity)getActivity()).setSortByMode("posts");

        RecyclerView recyclerView = view.findViewById(R.id.post_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<PostResponse> postArrayList = new ArrayList<>();

        postArrayList.add(post);

        recyclerView.setAdapter(new PostRecyclerAdapter(getActivity(), postArrayList, reactions,
                preferences.getString("username", null), view));

        //
        getPostFlairs();
        //


    }

    private void getPostFlairs() {

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_flairs);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        assert post.getFlairs() != null;
        if(!post.getFlairs().isEmpty())
            recyclerView.setAdapter(new FlairButtonRecyclerAdapter(getActivity(),
                    post.getFlairs()));
    }

}