package com.example.sr2_2020_android2021_projekat.adapters;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.model.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private List<Post> postsRecyclerView;

    public PostRecyclerAdapter(List<Post> posts) {

        this.postsRecyclerView = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_post_tile, parent,
                false);

        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //set all values here

        Post post = this.postsRecyclerView.get(position);

        holder.postCommunity.setText(post.getCommunityName());
        holder.postTitle.setText(post.getTitle());
        holder.postText.setText(post.getText());

        ///

        String postTimestampStripped = post.getCreationDate().substring(0, 10);


        ///

        holder.postTimestamp.setText(postTimestampStripped);

        // here you can add setOnClickListener for Post Details ...

        /*

            holder.postTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, SecondActivity.class));
            }

        });


        */

    }

    @Override
    public int getItemCount() {

        return postsRecyclerView.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView postCommunity;

        private final TextView postTitle;

        private final TextView postText;

        private final TextView postTimestamp;

        ViewHolder(View v) {

            super(v);

            postCommunity = v.findViewById(R.id.post_community);
            postTitle = v.findViewById(R.id.post_title);
            postText = v.findViewById(R.id.post_text);
            postTimestamp = v.findViewById(R.id.post_timestamp);

        }
    }
}
