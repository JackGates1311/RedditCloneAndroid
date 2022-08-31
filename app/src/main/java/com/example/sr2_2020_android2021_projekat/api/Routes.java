package com.example.sr2_2020_android2021_projekat.api;

import com.example.sr2_2020_android2021_projekat.model.Community;
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
import retrofit2.http.Path;

public interface Routes {

    @GET("posts/getAllPosts")
    Call<List<PostResponse>> getAllPosts();

    @POST("auth/register")
    Call<String> register(@Body RegisterUser registerUser);

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("posts/createPost")
    Call<String> createPost(@Header("Authorization") String authToken, @Body PostRequest postRequest);

    @GET("communities/name={name}")
    Call<List<Community>> getCommunityByName(@Path("name") String communityName);

    @GET("posts/communityName={name}")
    Call<List<PostResponse>> getPostsByCommunityName(@Path("name") String communityName);

    @GET("communities/getAllCommunities")
    Call<List<Community>> getAllCommunities();


}
