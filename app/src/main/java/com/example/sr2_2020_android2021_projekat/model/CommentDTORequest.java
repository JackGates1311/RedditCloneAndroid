package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class CommentDTORequest {

    private Long commentId;
    private String text;
    private Long postId;
    private Long repliedToCommentId;

    public CommentDTORequest(Long commentId, String text, Long postId, Long repliedToCommentId) {
        this.commentId = commentId;
        this.text = text;
        this.postId = postId;
        this.repliedToCommentId = repliedToCommentId;
    }

    public CommentDTORequest(String text, Long postId, Long repliedToCommentId) {
        this.text = text;
        this.postId = postId;
        this.repliedToCommentId = repliedToCommentId;
    }

    public CommentDTORequest(String text) {
        this.text = text;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getRepliedToCommentId() {
        return repliedToCommentId;
    }

    public void setRepliedToCommentId(Long repliedToCommentId) {
        this.repliedToCommentId = repliedToCommentId;
    }

    @NonNull
    @Override
    public String toString() {
        return "CommentDTORequest{" +
                "commentId=" + commentId +
                ", text='" + text + '\'' +
                ", postId=" + postId +
                ", repliedToCommentId=" + repliedToCommentId +
                '}';
    }
}
