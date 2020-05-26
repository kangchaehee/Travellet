package com.example.example.data;

import com.google.gson.annotations.SerializedName;

public class SignUpData {
    @SerializedName("userName")
    private String userName;
    @SerializedName("userEmail")
    private String userEmail;
    @SerializedName("userPwd")
    private String userPwd;
    @SerializedName("userSex")
    private String userSex;
    @SerializedName("userAge")
    private int userAge;
    @SerializedName("userCountry")
    private String userCountry;

    public SignUpData(String userName, String userEmail, String userPwd, String userSex, int userAge, String userCountry) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userSex = userSex;
        this.userAge = userAge;
        this.userCountry = userCountry;
    }
}
