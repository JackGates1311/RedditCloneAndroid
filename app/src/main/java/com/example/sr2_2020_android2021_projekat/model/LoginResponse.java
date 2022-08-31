package com.example.sr2_2020_android2021_projekat.model;

public class LoginResponse {

    private String authToken;
    private int expiresIn;
    private String role;

    public LoginResponse(String authToken, int expiresIn, String role) {

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
}
