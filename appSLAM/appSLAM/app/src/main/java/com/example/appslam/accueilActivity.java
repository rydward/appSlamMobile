package com.example.appslam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.appslam.annonce.AnnoncesFragment;
import com.example.appslam.chat.ChatFragment;
import com.example.appslam.chat.activityMessage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class accueilActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Fragment annoncesFragment, profileFragment, chatFragment;
    BottomNavigationView navigationView;
    String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        annoncesFragment = new AnnoncesFragment();
        profileFragment = new ProfileFragment();
        chatFragment = new ChatFragment();

        loadFragment(annoncesFragment);

        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("utilisateurId")!= null)
        {
            extra = bundle.getString("utilisateurId");
        }

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.action_annonces:
                fragment = annoncesFragment;
                break;
            case R.id.action_profile:
                fragment = profileFragment;
                break;
            case R.id.action_chat:
                Intent envoiMessage = new Intent(this, activityMessage.class);
                envoiMessage.putExtra("utilisateurId", extra);
                startActivity(envoiMessage);

                    fragment = chatFragment;
                break;

        }
        return loadFragment(fragment);
    }
}