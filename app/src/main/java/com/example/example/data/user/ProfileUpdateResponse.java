package com.example.example.data.user;

import com.google.gson.annotations.SerializedName;

public class ProfileUpdateResponse {
    @SerializedName("code")
    private int code;

    public int getCode() {
        return code;
    }
}
