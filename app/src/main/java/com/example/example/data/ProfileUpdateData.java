package com.example.example.data;

import com.google.gson.annotations.SerializedName;

public class ProfileUpdateData {

    @SerializedName("userName")
    private String userName;
    @SerializedName("userAge")
    private int userAge;
    @SerializedName("userCountry")
    private String userCountry;

    public ProfileUpdateData(String userName, int userAge, String userCountry) {
        this.userName = userName;
        this.userAge = userAge;
        this.userCountry = userCountry;
    }
}
