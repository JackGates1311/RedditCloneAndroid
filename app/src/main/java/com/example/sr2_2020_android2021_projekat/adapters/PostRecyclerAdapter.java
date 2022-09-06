package com.example.sr2_2020_android2021_projekat.adapters;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.fragments.CommunityFragment;
import com.example.sr2_2020_android2021_projekat.fragments.CreateEditPostFragment;
import com.example.sr2_2020_android2021_projekat.fragments.PostDetailsFragment;
import com.example.sr2_2020_android2021_projekat.fragments.PostsFragment;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.model.ReactionDTO;
import com.example.sr2_2020_android2021_projekat.tools.EnvironmentConfig;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private final List<PostResponse> postsRecyclerView;
    private final FragmentActivity fragmentActivity;
    private List<ReactionDTO> reactions;
    private final String username;
    private final View view;

    private final HttpClient httpClient = new HttpClient();

    public PostRecyclerAdapter(FragmentActivity fragmentActivity,
                               List<PostResponse> postResponses, List<ReactionDTO> reactions,
                               String username, View view) {

        this.postsRecyclerView = postResponses;
        this.fragmentActivity = fragmentActivity;
        this.reactions = reactions;
        this.username = username;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        httpClient.setContext(view.getContext());

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

        if(username == null)
            holder.postTileFooter.setVisibility(View.GONE);
        else {
            if(username.equals(postResponse.getUsername())) {
                holder.editPostButton.setVisibility(View.VISIBLE);
                holder.deletePostButton.setVisibility(View.VISIBLE);
            }
        }


        //posting image/

        holder.postImages.registerLifecycle(fragmentActivity.getLifecycle());

        if(!postResponse.getImages().isEmpty()) {
            holder.postImages.setVisibility(View.VISIBLE);

            List<CarouselItem> images = new ArrayList<>();

            for(String imageName : postResponse.getImages()) {

                String imageUrl = EnvironmentConfig.baseURL + "file/" + imageName;

                images.add(new CarouselItem(imageUrl));
            }

            holder.postImages.setData(images);
        }

        ///

        populateReactions(holder, postResponse);

        holder.postCommunity.setOnClickListener(view ->
                FragmentTransition.to(CommunityFragment.newInstance(
                        postResponse.getCommunityName()), fragmentActivity,
                true, R.id.viewPage));

        holder.postTitle.setOnClickListener(view ->
                FragmentTransition.to(PostDetailsFragment.newInstance(
                postResponse), fragmentActivity,
                true, R.id.viewPage));

        holder.downvoteButton.setOnClickListener(v -> {

            String reactionType = "DOWNVOTE";

            ReactionDTO reactionDTO = new ReactionDTO(reactionType, postResponse.getPostId());

            sendReaction(holder, postResponse, reactionType, reactionDTO);

        });

        holder.upvoteButton.setOnClickListener(v -> {

            String reactionType = "UPVOTE";

            ReactionDTO reactionDTO = new ReactionDTO(reactionType, postResponse.getPostId());

            sendReaction(holder, postResponse, reactionType, reactionDTO);

        });

        holder.editPostButton.setOnClickListener(v -> {

            ((MainActivity)fragmentActivity).setPostMode("EDIT");
            FragmentTransition.to(CreateEditPostFragment.newInstance(postResponse),
                    fragmentActivity, true, R.id.viewPage);
        });

        holder.deletePostButton.setOnClickListener(v -> {
            deletePost(view, postResponse.getPostId());
        });

    }

    private void deletePost(View view, Long postId) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.deletePost(postId), view, () ->
                Toast.makeText(view.getContext(), "Post deleted successfully",
                Toast.LENGTH_SHORT).show());

        FragmentTransition.to(PostsFragment.newInstance("hot"), fragmentActivity,
                true, R.id.viewPage);
    }

    private void sendReaction(@NonNull ViewHolder holder, PostResponse postResponse, String reactionType, ReactionDTO reactionDTO) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.sendReaction(reactionDTO), view,
                () -> {

            RetrofitRepository<PostResponse> postResponseRetrofitRepository =
                    new RetrofitRepository<>();

            postResponseRetrofitRepository.sendRequest(httpClient.routes.getPostById(postResponse.getPostId()),
                    view, () -> {

                        updateReactionList(postResponseRetrofitRepository,
                                reactionType);

                        populateReactions(holder,
                                postResponseRetrofitRepository.getResponseData());

                        holder.postReactionCount.setText(
                        String.valueOf(postResponseRetrofitRepository.getResponseData().
                                getReactionCount()));
            });
        });
    }

    private void updateReactionList(RetrofitRepository<PostResponse>
        postResponseRetrofitRepository, String reactionType) {
        for(ReactionDTO reaction : reactions) {

            if (postResponseRetrofitRepository.getResponseData().getPostId().
                    equals(reaction.getPostId())) {

                if(!Objects.isNull(reaction.getReactionType())) {

                    if(reaction.getReactionType().equals(reactionType))
                        reaction.setReactionType(null);
                    else
                        reaction.setReactionType(reactionType);

                }
                else {
                    reaction.setReactionType(reactionType);
                }
                break;
            }

        }
    }

    private void populateReactions(@NonNull ViewHolder holder, PostResponse postResponse) {
        if(!reactions.isEmpty()) {

            for(ReactionDTO reaction : reactions) {
                if(!Objects.isNull(reaction.getReactionType())) {
                    if(postResponse.getPostId().equals(reaction.getPostId()) &&
                            username.equals(reaction.getUsername()) &&
                            reaction.getReactionType().equals("UPVOTE")) {

                        holder.downvoteButton.setColorFilter(Color.parseColor(
                                "#3D7ADC"));
                        holder.upvoteButton.setColorFilter(Color.GREEN);
                        break;
                    }
                    if(postResponse.getPostId().equals(reaction.getPostId()) &&
                            username.equals(reaction.getUsername()) &&
                            reaction.getReactionType().equals("DOWNVOTE")) {

                        holder.downvoteButton.setColorFilter(Color.RED);
                        holder.upvoteButton.setColorFilter(Color.parseColor(
                                "#3D7ADC"));
                        break;
                    }
                } else {
                    holder.downvoteButton.clearColorFilter();
                    holder.upvoteButton.clearColorFilter();
                }

            }

        }
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

        private final ImageButton upvoteButton;

        private final ImageButton downvoteButton;

        private final LinearLayout postTileFooter;

        private final ImageCarousel postImages;

        private final ImageButton editPostButton;

        private final ImageButton deletePostButton;

        ViewHolder(View v) {

            super(v);

            postCommunity = v.findViewById(R.id.post_community);
            postTitle = v.findViewById(R.id.post_title);
            postText = v.findViewById(R.id.post_text);
            postTimestamp = v.findViewById(R.id.post_timestamp);
            postReactionCount = v.findViewById(R.id.post_reaction_count);
            postCommentCount = v.findViewById(R.id.post_comment_count);
            upvoteButton = v.findViewById(R.id.upvote_button);
            downvoteButton = v.findViewById(R.id.downvote_button);
            postTileFooter = v.findViewById(R.id.post_tile_footer);
            postImages = v.findViewById(R.id.post_images);
            editPostButton = v.findViewById(R.id.post_edit_button);
            deletePostButton = v.findViewById(R.id.post_delete_button);

        }
    }
}
