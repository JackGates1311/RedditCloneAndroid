package com.example.sr2_2020_android2021_projekat.api;

import com.example.sr2_2020_android2021_projekat.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderAPI {

    @GET("getAllPosts")
    Call<List<Post>> getAllPosts();
}
