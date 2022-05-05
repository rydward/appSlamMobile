package com.example.appslam.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appslam.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

public class activityMessage extends AppCompatActivity {

    String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("utilisateurId")!= null)
        {
            extra = bundle.getString("utilisateurId");
        }

    }

    public void onNavEnvoiMessage(View v){
        Intent envoiMessage = new Intent(this, activityEnvoiDesMessages.class);
        envoiMessage.putExtra("utilisateurId", extra);
        startActivity(envoiMessage);
    }

    public void onNavReceptionMessage(View v){
        Intent receptionMessage = new Intent(this, activityMessageRecu.class);
        receptionMessage.putExtra("utilisateurId", extra);
        startActivity(receptionMessage);
    }

    public static class JWTUtils {

        public static String getJson(String token) throws UnsupportedEncodingException{
            String[] split = token.split("\\.");
            byte[] decodedBytes = Base64.decode(split[1], Base64.URL_SAFE);
            return new String(decodedBytes, "UTF-8");
        }
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}