package com.example.appslam.annonce;

public class annoncesItem {

    private String designation;
    private String description;
    private Integer id;
    private Integer prix;


    public annoncesItem(String designation, String description, Integer id, Integer prix){
        this.designation = designation;
        this.description = description;
        this.id = id;
        this.prix = prix;
    }

    public String getDesignation(){ return designation;}

    public String getDescription(){ return description;}

    public Integer getId(){ return id;}

    public Integer getPrix(){ return prix;}
}
