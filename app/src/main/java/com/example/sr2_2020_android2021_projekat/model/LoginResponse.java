package com.example.sr2_2020_android2021_projekat.model;

public class LoginResponse {

    private String authToken;
    private int expiresIn;

    public LoginResponse(String authToken, int expiresIn) {

        this.authToken = authToken;
        this.expiresIn = expiresIn;
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
}
