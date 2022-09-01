package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

import java.util.List;

public class CommunityDTORequest {

    private Long communityId;
    private String name;
    private String description;
    private Boolean isSuspended;
    private String suspendedReason;
    private List<String> flairs;

    public CommunityDTORequest(Long communityId, String name, String description,
                               Boolean isSuspended, String suspendedReason, List<String> flairs) {
        this.communityId = communityId;
        this.name = name;
        this.description = description;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
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

    public List<String> getFlairs() {
        return flairs;
    }

    public void setFlairs(List<String> flairs) {
        this.flairs = flairs;
    }

    @NonNull
    @Override
    public String toString() {
        return "CommunityDTORequest{" +
                "communityId=" + communityId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isSuspended=" + isSuspended +
                ", suspendedReason='" + suspendedReason + '\'' +
                ", flairs=" + flairs +
                '}';
    }
}
