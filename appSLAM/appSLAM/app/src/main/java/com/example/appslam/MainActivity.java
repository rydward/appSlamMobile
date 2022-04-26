package com.example.appslam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onConnectAction(View v){
        Intent connexionPage = new Intent(this, connectActivity.class);
        startActivity(connexionPage);
    }

    public void onRegisterAction(View v){
        Intent inscrirePage = new Intent(this, inscrireActivity.class);
        startActivity(inscrirePage);
    }
}