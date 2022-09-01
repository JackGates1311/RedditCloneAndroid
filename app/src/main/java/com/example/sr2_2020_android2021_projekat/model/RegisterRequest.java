package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String avatar;
    private String description;
    private String displayName;
    private Boolean isAdministrator;

    public RegisterRequest(String username, String password, String email, String avatar,
                           String description, String displayName, Boolean isAdministrator) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.description = description;
        this.displayName = displayName;
        this.isAdministrator = isAdministrator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }

    @NonNull
    @Override
    public String toString() {
        return "RegisterUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                ", displayName='" + displayName + '\'' +
                ", isAdministrator=" + isAdministrator +
                '}';
    }
}
