package com.example.sr2_2020_android2021_projekat.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.fragments.PostDetailsFragment;
import com.example.sr2_2020_android2021_projekat.model.CommentDTORequest;
import com.example.sr2_2020_android2021_projekat.model.CommentDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.model.ReactionDTO;
import com.example.sr2_2020_android2021_projekat.tools.DialogHelper;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder>
{

    private final FragmentActivity fragmentActivity;
    private final List<CommentDTOResponse> commentDTOResponses;
    private final String username;
    private final View view;
    private final PostResponse post;

    private final HttpClient httpClient = new HttpClient();

    public CommentRecyclerAdapter(FragmentActivity activity, List<CommentDTOResponse>
            commentDTOResponses, String username, View view, PostResponse post) {
        this.fragmentActivity = activity;
        this.commentDTOResponses = commentDTOResponses;
        this.username = username;
        this.view = view;
        this.post = post;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        httpClient.setContext(view.getContext());

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_comment_tile,
                parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        CommentDTOResponse commentDTOResponse = this.commentDTOResponses.get(position);

        holder.commentUsername.setText(commentDTOResponse.getUsername());
        holder.timestampComment.setText(commentDTOResponse.getTimestamp().substring(0, 10));
        holder.textComment.setText(commentDTOResponse.getText());
        holder.reactionCountView.setText(String.valueOf(commentDTOResponse.getReactionCount()));

        if(commentDTOResponse.getUsername().equals(username)) {

            holder.commentEditButton.setVisibility(View.VISIBLE);
            holder.commentDeleteButton.setVisibility(View.VISIBLE);
        }

        holder.commentEditButton.setOnClickListener(v -> {

            DialogHelper dialogHelper = new DialogHelper();

            dialogHelper.showDialog(v.getContext(), "Edit comment:",
                    R.layout.fragment_edit_comment, () -> {

                        TextInputEditText commentText = dialogHelper.getCurrentDialog().
                                findViewById(R.id.edit_text_comment);

                        editComment(view, commentText, commentDTOResponse.getCommentId());

                    }, () -> {

                    }, () -> {

                    });

        });

        holder.commentDeleteButton.setOnClickListener(v -> {

            deleteComment(commentDTOResponse.getCommentId());

        });


        holder.upvoteButton.setOnClickListener(v -> {

            RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

            ReactionDTO reactionDTO = new ReactionDTO("UPVOTE", commentDTOResponse);

            retrofitRepository.sendRequest(httpClient.routes.sendReaction(reactionDTO), view,
                    () -> {

                FragmentTransition.to(PostDetailsFragment.newInstance(post), fragmentActivity,
                                false, R.id.viewPage);

            });

        });

        holder.downvoteButton.setOnClickListener(v -> {

            RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

            ReactionDTO reactionDTO = new ReactionDTO("DOWNVOTE", commentDTOResponse);

            retrofitRepository.sendRequest(httpClient.routes.sendReaction(reactionDTO), view,
                    () -> {

                        FragmentTransition.to(PostDetailsFragment.newInstance(post), fragmentActivity,
                                false, R.id.viewPage);

                    });

        });

    }

    private void deleteComment(Long commentId) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.deleteComment(commentId), view, () -> {
            Toast.makeText(view.getContext(), "Comment successfully deleted",
                    Toast.LENGTH_SHORT).show();

            FragmentTransition.to(PostDetailsFragment.newInstance(post), fragmentActivity,
                    false, R.id.viewPage);
        });
    }

    private void editComment(View view, TextInputEditText commentText, Long commentId) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        CommentDTORequest commentDTORequest = new CommentDTORequest(
                Objects.requireNonNull(commentText.getText()).toString());

        retrofitRepository.sendRequest(httpClient.routes.editComment(commentId, commentDTORequest),
                view, () -> {

                    Toast.makeText(view.getContext(), "Comment successfully edited",
                            Toast.LENGTH_SHORT).show();

                    FragmentTransition.to(PostDetailsFragment.newInstance(post), fragmentActivity,
                            false, R.id.viewPage);

                });

    }

    @Override
    public int getItemCount() {

        return commentDTOResponses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView commentUsername;
        private final TextView timestampComment;
        private final TextView textComment;
        private final TextView reactionCountView;
        private final ImageButton downvoteButton;
        private final ImageButton upvoteButton;
        private final ImageButton replyButtonComment;
        private final ImageButton reportButtonComment;
        private final ImageButton commentEditButton;
        private final ImageButton commentDeleteButton;

        ViewHolder(View v){
            super(v);

            commentUsername = v.findViewById(R.id.username_comment);
            timestampComment = v.findViewById(R.id.timestamp_comment);
            textComment = v.findViewById(R.id.text_comment);
            reactionCountView = v.findViewById(R.id.reaction_count_comment);
            downvoteButton = v.findViewById(R.id.downvote_button_comment);
            upvoteButton = v.findViewById(R.id.upvote_button_comment);
            replyButtonComment = v.findViewById(R.id.reply_button_comment);
            reportButtonComment = v.findViewById(R.id.report_button_comment);
            commentEditButton = v.findViewById(R.id.comment_edit_button);
            commentDeleteButton = v.findViewById(R.id.comment_delete_button);

        }

    }
}
