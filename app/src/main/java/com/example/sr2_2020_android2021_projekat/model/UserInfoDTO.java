package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class UserInfoDTO {

    private String username;
    private String email;
    private String avatar;
    private String displayName;
    private String description;
    private int karma;

    public UserInfoDTO(String username, String email, String avatar, String displayName,
                       String description, int karma) {
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.displayName = displayName;
        this.description = description;
        this.karma = karma;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    @Override
    @NonNull
    public String toString() {
        return "UserInfoDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", karma=" + karma +
                '}';
    }
}
