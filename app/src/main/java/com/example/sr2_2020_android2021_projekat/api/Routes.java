package com.example.sr2_2020_android2021_projekat.api;

import com.example.sr2_2020_android2021_projekat.model.ChangePasswordRequest;
import com.example.sr2_2020_android2021_projekat.model.CommentDTORequest;
import com.example.sr2_2020_android2021_projekat.model.CommentDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTORequest;
import com.example.sr2_2020_android2021_projekat.model.CommunityDTOResponse;
import com.example.sr2_2020_android2021_projekat.model.FileResponse;
import com.example.sr2_2020_android2021_projekat.model.FlairDTO;
import com.example.sr2_2020_android2021_projekat.model.LoginRequest;
import com.example.sr2_2020_android2021_projekat.model.AuthResponse;
import com.example.sr2_2020_android2021_projekat.model.PostRequest;
import com.example.sr2_2020_android2021_projekat.model.PostResponse;
import com.example.sr2_2020_android2021_projekat.model.ReactionDTO;
import com.example.sr2_2020_android2021_projekat.model.RegisterRequest;
import com.example.sr2_2020_android2021_projekat.model.ReportDTO;
import com.example.sr2_2020_android2021_projekat.model.UserInfoDTO;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Routes {

    @POST("auth/register")
    Call<String> register(@Body RegisterRequest registerRequest);

    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("communities/createCommunity")
    Call<String> createCommunity(@Body CommunityDTORequest communityDTORequest);

    @GET("communities/getAllCommunities")
    Call<List<CommunityDTOResponse>> getAllCommunities();

    @GET("communities/id={id}")
    Call<CommunityDTOResponse> getCommunityById(@Path("id") Long id);

    @POST("posts/createPost")
    Call<String> createPost(@Body PostRequest postRequest);

    @GET("posts/getAllPosts")
    Call<List<PostResponse>> getAllPosts(@Query("sortBy") String sortBy);

    @GET("posts/{id}")
    Call<PostResponse> getPostById(@Path("id") Long id);

    @PUT("communities/{id}")
    Call<CommunityDTORequest> editCommunity(@Path("id") Long id,
                                            @Body CommunityDTORequest communityDTORequest);

    @PUT("suspend/{id}")
    Call<CommunityDTORequest> suspendCommunity(@Path("id") Long id, @Body CommunityDTORequest
            communityDTORequest);

    @PUT("posts/{id}")
    Call<PostRequest> editPost(@Path("id") Long id, @Body PostRequest postRequest);

    @DELETE("posts/{id}")
    Call<?> deletePost(@Path("id") Long id);

    @PUT("auth/changePassword")
    Call<ChangePasswordRequest> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @GET("communities/name={name}")
    Call<CommunityDTOResponse> getCommunityByName(@Path("name") String communityName);

    @GET("posts/communityName={name}")
    Call<List<PostResponse>> getPostsByCommunityName(@Path("name") String communityName,
                                                     @Query("sortBy") String sortBy);

    @GET("reactions")
    Call<List<ReactionDTO>> getReactionsByUsername(@Body ReactionDTO reactionDTO);

    @POST("reactions")
    Call<String> sendReaction(@Body ReactionDTO reactionDTO);

    @GET("auth/accountInfo")
    Call<UserInfoDTO> getAccountInfo();

    @PUT("auth/updateAccountInfo")
    Call<?> updateAccountInfo(@Body UserInfoDTO userInfoDTO);

    @POST("comments/postComment")
    Call<String> postComment(@Body CommentDTORequest commentDTORequest);

    @GET("comments/getPostComments/{id}")
    Call<List<CommentDTOResponse>> getPostComments(@Query("sortBy") String sortBy,
                                                   @Path("id") Long id);

    @PUT("comments/{id}")
    Call<CommentDTOResponse> editComment(@Path("id") Long id,
                                         @Body CommentDTORequest commentDTORequest);

    @DELETE("comments/{id}")
    Call<String> deleteComment(@Path("id") Long id);

    @GET("comments/{id}")
    Call<CommentDTOResponse> getCommentById(@Path("id") Long id);

    @POST("file/upload")
    Call<FileResponse> uploadFile(@Part MultipartBody.Part multipartFile);

    @POST("file/upload")
    Call<FileResponse> uploadFile(@Query("postId") Long id,
                                  @Part MultipartBody.Part multipartFile);

    @GET("file/{filename}")
    Call<ResponseBody> getFile(@Path("filename") String filename);

    @PUT("file/upload")
    Call<FileResponse> changeAvatar(@Part MultipartBody.Part multipartFile);

    @DELETE("file/delete/{filename}")
    Call<ResponseBody> deleteFile(@Path("filename") String filename);

    @POST("flair")
    Call<String> addFlair(@Body FlairDTO flairDTO);

    @GET("flair")
    Call<List<FlairDTO>> getAllFlairs();

    @GET("flair/{id}")
    Call<FlairDTO> getFlairById(@Path("id") Long id);

    @PUT("flair/{name}")
    Call<FlairDTO> editFlair(@Path("name") String flairName);

    @DELETE("flair/{name}")
    Call<String> deleteFlair(@Path("name") String flairName);

    @POST("reports")
    Call<String> sendReport(@Body ReportDTO reportDTO);
}
