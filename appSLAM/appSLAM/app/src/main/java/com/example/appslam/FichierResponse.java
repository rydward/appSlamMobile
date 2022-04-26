package com.example.appslam;

import com.google.gson.annotations.SerializedName;

public class FichierResponse {
    @SerializedName("id")
    private Integer id;

    @SerializedName("nom")
    private String nom;

    @SerializedName("extension")
    private String extension;

    @SerializedName("taille")
    private Integer taille;

    @SerializedName("original")
    private String original;

    @SerializedName("annonce")
    private String annonce;

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getExtension() {
        return extension;
    }

    public Integer getTaille() {
        return taille;
    }

    public String getOriginal() {
        return original;
    }

    public String getAnnonce() {
        return annonce;
    }
}
