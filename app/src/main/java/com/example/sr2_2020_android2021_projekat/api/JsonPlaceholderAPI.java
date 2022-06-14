package com.example.sr2_2020_android2021_projekat.api;

import com.example.sr2_2020_android2021_projekat.model.LoginRequest;
import com.example.sr2_2020_android2021_projekat.model.LoginResponse;
import com.example.sr2_2020_android2021_projekat.model.PostRequest;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.model.RegisterUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonPlaceholderAPI {

    @GET("posts/getAllPosts")
    Call<List<PostResponse>> getAllPosts();

    @POST("auth/register")
    Call<String> register(@Body RegisterUser registerUser);

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("posts/createPost")
    Call<String> createPost(@Header("Authorization") String authToken, @Body PostRequest postRequest);
}
