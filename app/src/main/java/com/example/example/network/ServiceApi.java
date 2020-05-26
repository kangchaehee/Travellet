package com.example.example.network;

import com.example.example.data.user.SignUpData;
import com.example.example.data.user.SignUpResponse;
import com.example.example.data.user.SignInData;
import com.example.example.data.user.SignInResponse;
import com.example.example.data.user.ProfileReadResponse;
import com.example.example.data.user.ProfileUpdateData;
import com.example.example.data.user.ProfileUpdateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

// 서비스 인터페이스
public interface ServiceApi {

    //회원가입
    @POST("/users/signup")
    Call<SignUpResponse> userSignUp(@Body SignUpData data);
    //로그인
    @POST("/users/signin")
    Call<SignInResponse> userSignIn(@Body SignInData data);
    //프로필 Read
    @GET("/users/{id}/profile")
    Call<ProfileReadResponse> userProfileRead(@Path("id") int userId);
    //프로필 Update
    @PUT("/users/{id}/profile")
    Call<ProfileUpdateResponse> userProfileUpdate(
            @Path("id") int userId,
            @Body ProfileUpdateData data
            );

}