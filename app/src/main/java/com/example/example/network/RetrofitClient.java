package com.example.example.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 네트워크 관련 서비스 객체 생성
public class RetrofitClient {
    private final static String BASE_URL = "http://:3000";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
