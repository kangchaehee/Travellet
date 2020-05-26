package com.example.example.data.user;


import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("userId")
    private int userId;
    @SerializedName("userCountry")
    private String userCountry;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserCountry() {
        return userCountry;
    }

}
