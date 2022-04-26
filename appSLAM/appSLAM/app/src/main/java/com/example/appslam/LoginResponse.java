package com.example.appslam;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    private String token;

    @SerializedName("data")
    private Data data;

    public String getToken() {
        return token;
    }

    public Data getData() {
        return data;
    }



}
