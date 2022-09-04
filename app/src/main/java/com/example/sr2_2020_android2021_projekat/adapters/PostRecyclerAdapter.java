package com.example.sr2_2020_android2021_projekat.adapters;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.fragments.CommunityFragment;
import com.example.sr2_2020_android2021_projekat.fragments.PostDetailsFragment;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;

import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private final List<PostResponse> postsRecyclerView;

    private final FragmentActivity fragmentActivity;

    public PostRecyclerAdapter(FragmentActivity fragmentActivity,
                               List<PostResponse> postResponses) {

        this.postsRecyclerView = postResponses;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_post_tile,
                parent, false);

        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PostResponse postResponse = this.postsRecyclerView.get(position);

        holder.postCommunity.setText(postResponse.getCommunityName());
        holder.postTitle.setText(postResponse.getTitle());
        holder.postText.setText(postResponse.getText());
        holder.postReactionCount.setText(String.valueOf(postResponse.getReactionCount()));
        holder.postCommentCount.setText(String.valueOf(postResponse.getCommentCount()));

        String postTimestampStripped = postResponse.getCreationDate().substring(0, 10);

        holder.postTimestamp.setText(postTimestampStripped);

        holder.postCommunity.setOnClickListener(view ->
                FragmentTransition.to(CommunityFragment.newInstance(
                        postResponse.getCommunityName()), fragmentActivity,
                true, R.id.viewPage));

        holder.postTitle.setOnClickListener(view ->
                FragmentTransition.to(PostDetailsFragment.newInstance(
                postResponse.getPostId()), fragmentActivity,
                true, R.id.viewPage));

    }

    @Override
    public int getItemCount() {

        return postsRecyclerView.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView postCommunity;

        private final TextView postTitle;

        private final TextView postText;

        private final TextView postTimestamp;

        private final TextView postReactionCount;

        private final TextView postCommentCount;

        ViewHolder(View v) {

            super(v);

            postCommunity = v.findViewById(R.id.post_community);
            postTitle = v.findViewById(R.id.post_title);
            postText = v.findViewById(R.id.post_text);
            postTimestamp = v.findViewById(R.id.post_timestamp);
            postReactionCount = v.findViewById(R.id.post_reaction_count);
            postCommentCount = v.findViewById(R.id.post_comment_count);

        }
    }
}
