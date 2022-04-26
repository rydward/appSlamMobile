package com.example.appslam.annonce;

public class Annonce {
    Integer id;
    String designation;
    String description;
    String datepost;
    String ville;
    String region;
    String codepostal;
    String nom;
    String mail;
    String telephone;
    String utilisateur;
    String[] type;
    String[] fichier;
    Integer prix;

    public Annonce(Integer id, String designation, String description, String datepost, String ville, String region, String codepostal, String nom, String mail, String telephone, String utilisateur, String[] type, String[] fichier, Integer prix) {
        this.id = id;
        this.designation = designation;
        this.description = description;
        this.datepost = datepost;
        this.ville = ville;
        this.region = region;
        this.codepostal = codepostal;
        this.nom = nom;
        this.mail = mail;
        this.telephone = telephone;
        this.utilisateur = utilisateur;
        this.type = type;
        this.fichier = fichier;
        this.prix = prix;
    }

    public Integer getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDescription() {
        return description;
    }

    public String getDatepost() {
        return datepost;
    }

    public String getVille() {
        return ville;
    }

    public String getRegion() {
        return region;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public String getNom() {
        return nom;
    }

    public String getMail() {
        return mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String[] getType() {
        return type;
    }

    public String[] getFichier() {
        return fichier;
    }

    public Integer getPrix() {
        return prix;
    }
}
