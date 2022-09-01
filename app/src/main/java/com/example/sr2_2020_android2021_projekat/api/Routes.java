package com.example.sr2_2020_android2021_projekat.api;

import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.LoginRequest;
import com.example.sr2_2020_android2021_projekat.model.AuthResponse;
import com.example.sr2_2020_android2021_projekat.model.PostRequest;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.model.RegisterRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Routes {

    @GET("posts/getAllPosts")
    Call<List<PostResponse>> getAllPosts();

    @POST("auth/register")
    Call<String> register(@Body RegisterRequest registerRequest);

    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("posts/createPost")
    //Call<String> createPost(@Header("Authorization") String authToken, @Body PostRequest postRequest);
    Call<String> createPost(@Body PostRequest postRequest);

    @GET("communities/name={name}")
    Call<CommunityDTOResponse> getCommunityByName(@Path("name") String communityName);

    @GET("posts/communityName={name}")
    Call<List<PostResponse>> getPostsByCommunityName(@Path("name") String communityName);

    @GET("communities/getAllCommunities")
    Call<List<CommunityDTOResponse>> getAllCommunities();


}
