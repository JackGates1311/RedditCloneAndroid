package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

import java.util.List;

public class PostRequest {

    //TODO (LOW PRIORITY) merge DTO Response and DTO Request to one big DTO (for all DTOs)...
    //TODO implement all model fields, constructor, getters, setters ...
    //TODO implement all routes in Routes
    //TODO update post data

    private Long postId;
    private String communityName;
    private String creationDate;
    private String text;
    private String title;
    private Integer reactionCount;
    private List<String> flairs;

    public PostRequest(String communityName, String text, String title, List<String> flairs) {
        this.communityName = communityName;
        this.text = text;
        this.title = title;
        this.flairs = flairs;
    }

    public PostRequest(String text, String title, List<String> flairs) {
        this.text = text;
        this.title = title;
        this.flairs = flairs;
    }

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

    public Integer getReactionCount() {
        return reactionCount;
    }

    public void setReactionCount(Integer reactionCount) {
        this.reactionCount = reactionCount;
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
        return "PostRequest{" +
                "postId=" + postId +
                ", communityName='" + communityName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", reactionCount=" + reactionCount +
                ", flairs=" + flairs +
                '}';
    }
}
