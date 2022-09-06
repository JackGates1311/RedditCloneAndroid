package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

import java.util.List;

public class CommentDTOResponse {

    private Long commentId;
    private String text;
    private String timestamp;
    private Long postId;
    private Long userId;
    private String username;
    private List<CommentDTOResponse> replies;
    private Boolean isDeleted;
    private Integer reactionCount;

    public CommentDTOResponse(Long commentId, String text, String timestamp, Long postId,
                              Long userId, String username, List<CommentDTOResponse> replies,
                              Boolean isDeleted, Integer reactionCount) {
        this.commentId = commentId;
        this.text = text;
        this.timestamp = timestamp;
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.replies = replies;
        this.isDeleted = isDeleted;
        this.reactionCount = reactionCount;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public List<CommentDTOResponse> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentDTOResponse> replies) {
        this.replies = replies;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
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
        return "CommentDTOResponse{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", postId=" + postId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", replies=" + replies +
                ", isDeleted=" + isDeleted +
                ", reactionCount=" + reactionCount +
                '}';
    }
}
