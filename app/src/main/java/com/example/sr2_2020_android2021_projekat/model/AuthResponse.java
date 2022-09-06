package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class AuthResponse {

    private String authToken;
    private int expiresIn;
    private String role;

    public AuthResponse(String authToken, int expiresIn, String role) {

        this.authToken = authToken;
        this.expiresIn = expiresIn;
        this.role = role;
    }

    public String getAuthToken() {

        return authToken;
    }

    public void setAuthToken(String authToken) {

        this.authToken = authToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NonNull
    @Override
    public String toString() {
        return "AuthResponse{" +
                "authToken='" + authToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", role='" + role + '\'' +
                '}';
    }
}
