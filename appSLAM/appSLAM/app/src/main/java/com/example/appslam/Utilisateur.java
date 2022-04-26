package com.example.appslam;

public class Utilisateur {
    Integer id;
    String nom;
    String prenom;
    String[] messagerecu;
    String[] messageenvoi;


    public Utilisateur(Integer id, String nom, String prenom, String[] messagerecu, String[] messageenvoi) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.messagerecu = messagerecu;
        this.messageenvoi = messageenvoi;
    }

    public Integer getId() {return id;}

    public String getNom() {return nom;}

    public String getPrenom() {return prenom;}

    public String[] getMessagerecu() {return messagerecu;}

    public String[] getMessageenvoi() {return messageenvoi;}


}