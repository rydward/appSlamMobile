package com.example.appslam.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appslam.FichierResponse;
import com.example.appslam.R;
import com.example.appslam.RetrofitClient;
import com.example.appslam.Utilisateur;
import com.example.appslam.annonce.Annonce;
import com.example.appslam.annonce.AnnonceAfficheActivity;
import com.example.appslam.annonce.AnnonceItemAdapter;
import com.example.appslam.annonce.AnnoncesResponse;
import com.example.appslam.annonce.annoncesItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityEnvoiDesMessages extends AppCompatActivity {

    private ListView listViewMessageEnvoi;
    private List<Chat> listMessageEnvoi;

    String extra;
    TextView textViewEnvoi;
    TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoi_des_messages);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("utilisateurId")!= null)
        {
            this.extra = bundle.getString("utilisateurId");
        }

        textViewEnvoi = findViewById(R.id.Destinataire);
        textViewMessage = findViewById(R.id.message);

        Call<Utilisateur> callUtilisateur = RetrofitClient.getInstance().getApi().getUtilisateur(extra);

        callUtilisateur.enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> callUtilisateur, Response<Utilisateur> response) {

                for (int i = 0; i < response.body().getMessageenvoi().length; i++) {
                    Call<Chat> callChat = RetrofitClient.getInstance()
                            .getApi()
                            .getMessage(response.body().getMessageenvoi()[i].substring(30));

                    callChat.enqueue(new Callback<Chat>() {
                        @Override
                        public void onResponse(Call<Chat> callChat, Response<Chat> response3) {
                            //textViewEnvoi.setText(response3.body().get + response.body().get);
                            textViewMessage.setText(response3.body().getContenu());
                        }

                        @Override
                        public void onFailure(Call<Chat> call, Throwable t) {
                            Toast.makeText(activityEnvoiDesMessages.this, "Erreur lors de l'appel message", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {
                Toast.makeText(activityEnvoiDesMessages.this, "Erreur lors de l'appel utilisateur", Toast.LENGTH_LONG).show();
            }
        });

    }

}