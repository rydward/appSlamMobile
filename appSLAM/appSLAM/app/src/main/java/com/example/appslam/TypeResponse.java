package com.example.appslam;

import com.google.gson.annotations.SerializedName;

public class TypeResponse {
    @SerializedName("id")
    private Integer id;

    @SerializedName("libelle")
    private String libelle;

    @SerializedName("annonces")
    private String[] annonces;

    public Integer getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String[] getAnnonces() {
        return annonces;
    }
}
