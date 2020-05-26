package com.example.example.data;

import com.google.gson.annotations.SerializedName;

public class ProfileReadData {
    @SerializedName("userId")
    int userId;

    public ProfileReadData(int userId) {
        this.userId = userId;
    }
}
