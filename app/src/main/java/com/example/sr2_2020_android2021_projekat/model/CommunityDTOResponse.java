package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

import java.util.List;

public class CommunityDTOResponse {

    private Long communityId;
    private String name;
    private String description;
    private String creationDate;
    private Boolean isSuspended;
    private String suspendedReason;
    private int numberOfPosts;
    private List<String> flairs;

    public CommunityDTOResponse(Long communityId, String name, String description, String creationDate,
                                Boolean isSuspended, String suspendedReason, int numberOfPosts,
                                List<String> flairs) {

        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        this.numberOfPosts = numberOfPosts;
        this.flairs = flairs;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getSuspended() {
        return isSuspended;
    }

    public void setSuspended(Boolean suspended) {
        isSuspended = suspended;
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedReason) {
        this.suspendedReason = suspendedReason;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
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
        return "Community{" +
                "communityId=" + communityId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", isSuspended=" + isSuspended +
                ", suspendedReason='" + suspendedReason + '\'' +
                ", numberOfPosts=" + numberOfPosts +
                ", flairs=" + flairs +
                '}';
    }
}
