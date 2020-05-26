package com.example.example.data;

import com.google.gson.annotations.SerializedName;

public class ProfileData {
    @SerializedName("userId")
    int userId;

    public ProfileData(int userId) {
        this.userId = userId;
    }
}
