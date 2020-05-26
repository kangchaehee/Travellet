package com.example.example.network;

import com.example.example.data.user.JoinData;
import com.example.example.data.user.JoinResponse;
import com.example.example.data.user.LoginData;
import com.example.example.data.user.LoginResponse;
import com.example.example.data.user.ProfileData;
import com.example.example.data.user.ProfileResponse;
import com.example.example.data.user.ProfileUpdateData;
import com.example.example.data.user.ProfileUpdateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// 서비스 인터페이스
public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/profile")
    Call<ProfileResponse> userProfile(@Body ProfileData data);

    @POST("/user/profileUpdate")
    Call<ProfileUpdateResponse> userProfileUpdate(@Body ProfileUpdateData data);

}