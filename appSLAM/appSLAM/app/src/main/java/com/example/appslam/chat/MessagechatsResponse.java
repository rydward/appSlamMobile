package com.example.appslam.chat;

import com.google.gson.annotations.SerializedName;

public class MessagechatsResponse {
    @SerializedName("hydra:member")
    public Chat[] messageChats;
}
