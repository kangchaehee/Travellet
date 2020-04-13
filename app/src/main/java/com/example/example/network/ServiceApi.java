package com.example.example.network;

import com.example.example.data.JoinData;
import com.example.example.data.JoinResponse;
import com.example.example.data.LoginData;
import com.example.example.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}