package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

import java.util.List;

public class PostResponse {

    private Long postId;
    private String communityName;
    private String creationDate;
    private String text;
    private String title;
    private String username;
    private Integer reactionCount;
    private Integer commentCount;
    private List<String> images;
    private List<String> flairs;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getReactionCount() {
        return reactionCount;
    }

    public void setReactionCount(Integer reactionCount) {
        this.reactionCount = reactionCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getFlairs() {
        return flairs;
    }

    public void setFlairs(List<String> flairs) {
        this.flairs = flairs;
    }

    @NonNull
    @Override
    public String toString() {

        return "Post{" +
                "postId=" + postId +
                ", communityName='" + communityName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", reactionCount=" + reactionCount +
                '}';
    }
}
