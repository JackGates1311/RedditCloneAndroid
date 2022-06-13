package com.example.sr2_2020_android2021_projekat.model;

public class Post {

    private Long postId;
    private String communityName;
    private String creationDate;
    private String imagePath;
    private String text;
    private String title;
    private String username;
    private Integer reactionCount;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    @Override
    public String toString() {

        return "Post{" +
                "postId=" + postId +
                ", communityName='" + communityName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", reactionCount=" + reactionCount +
                '}';
    }
}
