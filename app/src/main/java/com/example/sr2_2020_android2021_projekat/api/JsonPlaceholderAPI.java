package com.example.sr2_2020_android2021_projekat.api;

import com.example.sr2_2020_android2021_projekat.model.Post;
import com.example.sr2_2020_android2021_projekat.model.RegisterUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceholderAPI {

    @GET("posts/getAllPosts")
    Call<List<Post>> getAllPosts();

    @POST("auth/register")
    Call<String> register(@Body RegisterUser registerUser);

}
