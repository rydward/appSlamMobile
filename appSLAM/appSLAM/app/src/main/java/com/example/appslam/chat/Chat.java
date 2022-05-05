package com.example.appslam.chat;

import java.util.Date;
import java.util.List;

public class Chat {
    Integer id;
    String contenu;
    String dateenvoi;
    String userrecoi;
    String userenvoi;

    public Chat(Integer id, String contenu, String dateenvoi, String userrecoi, String userenvoi) {
        this.id = id;
        this.contenu = contenu;
        this.dateenvoi = dateenvoi;
        this.userrecoi = userrecoi;
        this.userenvoi = userenvoi;
    }

    public Chat(List<Chat> messageEnvoiItemsList) {
    }

    public Integer getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDateenvoi() {
        return dateenvoi;
    }

    public String getUserrecoi() {
        return userrecoi;
    }

    public String getUserenvoi() {
        return userenvoi;
    }

}
