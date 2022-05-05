package com.example.appslam.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
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

public class activityMessageRecu extends AppCompatActivity {

    private ListView listViewMessageRecu;
    private List<Chat> messageRecutemsList = new ArrayList<>();
    private Context context;
    private String nomUserRecoi;

    String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_recu);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("utilisateurId")!= null)
        {
            this.extra = bundle.getString("utilisateurId");
        }

        this.context = this;

        Call<Utilisateur> callUtilisateur = RetrofitClient.getInstance().getApi().getUtilisateur(extra);

        callUtilisateur.enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> callUtilisateur, Response<Utilisateur> response) {

                for (int i = 0; i < response.body().getMessagerecu().length; i++) {
                    Call<Chat> callChat = RetrofitClient.getInstance()
                            .getApi()
                            .getMessage(response.body().getMessagerecu()[i].substring(30));

                    callChat.enqueue(new Callback<Chat>() {


                        @Override
                        public void onResponse(Call<Chat> callChat, Response<Chat> response2) {

                            Call<Utilisateur> callUtilisateurRecoi = RetrofitClient.getInstance().getApi().getUtilisateur(response2.body().getUserenvoi().substring(30));

                            callUtilisateurRecoi.enqueue(new Callback<Utilisateur>() {
                                @Override
                                public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                                    nomUserRecoi = response.body().getNom();
                                    messageRecutemsList.add(new Chat(response2.body().getId(), response2.body().getContenu(),
                                            response2.body().getDateenvoi(), response.body().getNom()+" "+response.body().getPrenom(), response2.body().getUserenvoi()));

                                    listViewMessageRecu = findViewById(R.id.listViewMessageRecu);
                                    listViewMessageRecu.setAdapter(new messageRecuAdapter(context, messageRecutemsList));
                                }

                                @Override
                                public void onFailure(Call<Utilisateur> call, Throwable t) {

                                    Toast.makeText(activityMessageRecu.this, "Erreur lors de l'appel NomUtilisateur", Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Chat> call, Throwable t) {
                            Toast.makeText(activityMessageRecu.this, "Erreur lors de l'appel message", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {
                Toast.makeText(activityMessageRecu.this, "Erreur lors de l'appel utilisateur", Toast.LENGTH_LONG).show();
            }
        });


    }

}