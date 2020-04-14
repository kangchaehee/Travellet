package com.example.example.data;

import com.google.gson.annotations.SerializedName;

public class ProfileUpdateData {
    @SerializedName("userId")
    int userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userAge")
    private int userAge;

    @SerializedName("userCountry")
    private String userCountry;

    public ProfileUpdateData(int userId, String userName, int userAge, String userCountry) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userCountry = userCountry;
    }
}
