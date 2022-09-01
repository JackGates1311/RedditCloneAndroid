package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class ReportDTO {

    private Long reportId;
    private String reportReason;
    private String reportDescription;
    private Boolean accepted;
    private Long userId;
    private Long postId;
    private Long commentId;

    public ReportDTO(Long reportId, String reportReason, String reportDescription,
                     Boolean accepted, Long userId, Long postId, Long commentId) {
        this.reportId = reportId;
        this.reportReason = reportReason;
        this.reportDescription = reportDescription;
        this.accepted = accepted;
        this.userId = userId;
        this.postId = postId;
        this.commentId = commentId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @NonNull
    @Override
    public String toString() {
        return "ReportDTO{" +
                "reportId=" + reportId +
                ", reportReason='" + reportReason + '\'' +
                ", reportDescription='" + reportDescription + '\'' +
                ", accepted=" + accepted +
                ", userId=" + userId +
                ", postId=" + postId +
                ", commentId=" + commentId +
                '}';
    }
}
