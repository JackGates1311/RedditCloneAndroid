package com.example.sr2_2020_android2021_projekat.model;

public class PostRequest {

    private String communityName;
    private String imagePath;
    private String text;
    private String title;

    public PostRequest(String communityName, String imagePath, String text, String title) {

        this.communityName = communityName;
        this.imagePath = imagePath;
        this.text = text;
        this.title = title;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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
}
