package com.example.appslam;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("email")
    private String email;

    @SerializedName("roles")
    private String[] roles;

    @SerializedName("password")
    private String password;

    @SerializedName("userIdentifier")
    private String userIdentifier;

    @SerializedName("username")
    private String username;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String[] getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public String getUsername() {
        return username;
    }
}
