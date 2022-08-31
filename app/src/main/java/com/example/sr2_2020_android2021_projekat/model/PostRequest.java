package com.example.sr2_2020_android2021_projekat.model;

import java.util.List;

public class PostRequest {

    //TODO (LOW PRIORITY) merge DTO Response and DTO Request to one big DTO (for all DTOs)...
    //TODO implement all model fields, constructor, getters, setters ...
    //TODO implement all routes in Routes
    //TODO update post data

    private String communityName;
    private String text;
    private String title;
    private List<String> flairs;

    public PostRequest(String communityName, String text, String title, List<String> flairs) {
        this.communityName = communityName;
        this.text = text;
        this.title = title;
        this.flairs = flairs;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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

    public List<String> getFlairs() {
        return flairs;
    }

    public void setFlairs(List<String> flairs) {
        this.flairs = flairs;
    }
}
