package com.example.appslam.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appslam.R;
import com.example.appslam.RetrofitClient;
import com.example.appslam.Utilisateur;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityEnvoiDesMessages extends AppCompatActivity {

    private ListView listViewMessageEnvoi;
    private List<Chat> messageEnvoiItemsList = new ArrayList<>();
    private Context context;

    String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoi_des_messages);

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

                for (int i = 0; i < response.body().getMessageenvoi().length; i++) {

                    String[] tab = new String[response.body().getMessageenvoi().length];
                    tab[i] = response.body().getMessageenvoi()[i].substring(30);

                    Call<Chat> callChat = RetrofitClient.getInstance()
                            .getApi()
                            .getMessage(response.body().getMessageenvoi()[i].substring(30));

                    callChat.enqueue(new Callback<Chat>() {


                        @Override
                        public void onResponse(Call<Chat> callChat, Response<Chat> response2) {

                            Call<Utilisateur> callUtilisateurRecoi = RetrofitClient.getInstance().getApi().getUtilisateur(response2.body().getUserrecoi().substring(30));

                            callUtilisateurRecoi.enqueue(new Callback<Utilisateur>() {
                                @Override
                                public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                                    messageEnvoiItemsList.add(new Chat(response2.body().getId(), response2.body().getContenu(),
                                            response2.body().getDateenvoi(), response.body().getNom()+" "+response.body().getPrenom(), response2.body().getUserenvoi()));

                                    listViewMessageEnvoi = findViewById(R.id.listViewMessageEnvoi);
                                    listViewMessageEnvoi.setAdapter(new messageEnvoiAdapter(context, messageEnvoiItemsList));
                                }

                                @Override
                                public void onFailure(Call<Utilisateur> call, Throwable t) {

                                    Toast.makeText(activityEnvoiDesMessages.this, "Erreur lors de l'appel NomUtilisateur", Toast.LENGTH_LONG).show();
                                }
                            });
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