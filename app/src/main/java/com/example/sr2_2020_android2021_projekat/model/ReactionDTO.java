package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class ReactionDTO {

    private Long reactionId;
    private String reactionType;
    private Long postId;
    private Long commentId;
    private Long userId;
    private String username;
    private Integer reactionCount;

    public ReactionDTO(Long reactionId, String reactionType, Long postId, Long commentId,
                       Long userId, String username, Integer reactionCount) {
        this.reactionId = reactionId;
        this.reactionType = reactionType;
        this.postId = postId;
        this.commentId = commentId;
        this.userId = userId;
        this.username = username;
        this.reactionCount = reactionCount;
    }

    public Long getReactionId() {
        return reactionId;
    }

    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @NonNull
    @Override
    public String toString() {
        return "ReactionDTO{" +
                "reactionId=" + reactionId +
                ", reactionType='" + reactionType + '\'' +
                ", postId=" + postId +
                ", commentId=" + commentId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", reactionCount=" + reactionCount +
                '}';
    }
}
