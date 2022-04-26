package com.example.appslam.annonce;

import com.google.gson.annotations.SerializedName;

public class AnnoncesResponse {
    @SerializedName("hydra:member")
    public Annonce[] annonces;
}