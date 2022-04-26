package com.example.appslam.annonce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appslam.FichierResponse;
import com.example.appslam.R;
import com.example.appslam.RetrofitClient;
import com.example.appslam.TypeResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnonceAfficheActivity extends AppCompatActivity {

    TextView textViewDesignation, textViewDescription, textViewDatepost, textViewLieu, textViewPrix, textViewTelephone, textViewMail, textViewType;
    ImageView imageViewFichier1, imageViewFichier2, imageviewFichier3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_affiche);
        String idAnnonce = getIntent().getExtras().getString("idAnnonce");
        textViewDesignation = findViewById(R.id.textViewDesignation);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDatepost = findViewById(R.id.textViewDatepost);
        textViewLieu = findViewById(R.id.textViewLieu);
        textViewPrix = findViewById(R.id.textViewPrix);
        textViewTelephone = findViewById(R.id.textViewTelephone);
        textViewMail = findViewById(R.id.textViewMail);
        textViewType = findViewById(R.id.textViewTypes);
        imageViewFichier1 = findViewById(R.id.imageViewFichier1);
        imageViewFichier2 = findViewById(R.id.imageViewFichier2);
        imageviewFichier3 = findViewById(R.id.imageViewFichier3);

        Call<Annonce> call = RetrofitClient.getInstance()
                .getApi()
                .getAnnonce(idAnnonce);

        call.enqueue(new Callback<Annonce>() {
            @Override
            public void onResponse(Call<Annonce> call, Response<Annonce> response) {
                textViewDesignation.setText(response.body().getDesignation());
                textViewDescription.setText(response.body().getDescription());
                textViewDatepost.setText(response.body().getDatepost().substring(0, 10));
                textViewLieu.setText(response.body().getCodepostal() + " " + response.body().getVille());
                textViewPrix.setText(response.body().getPrix() + "€");
                textViewTelephone.setText(response.body().getTelephone());
                textViewMail.setText(response.body().getMail());

                for (int i = 0; i < response.body().getType().length; i++) {
                    Call<TypeResponse> call2 = RetrofitClient.getInstance()
                            .getApi()
                            .getType(response.body().getType()[i].substring(23));

                    call2.enqueue(new Callback<TypeResponse>() {
                        @Override
                        public void onResponse(Call<TypeResponse> call, Response<TypeResponse> response2) {
                            textViewType.append(" " + response2.body().getLibelle());
                        }

                        @Override
                        public void onFailure(Call<TypeResponse> call, Throwable t) {
                            Toast.makeText(AnnonceAfficheActivity.this, "Erreur lors de l'appel", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                for (int i = 0; i < response.body().getFichier().length; i++) {
                    Call<FichierResponse> call3 = RetrofitClient.getInstance()
                            .getApi()
                            .getFichier(response.body().getFichier()[i].substring(26));
                    int finalI = i;
                    call3.enqueue(new Callback<FichierResponse>() {
                        @Override
                        public void onResponse(Call<FichierResponse> call3, Response<FichierResponse> response3) {
                            if (finalI == 0) {
                                Picasso.get().load("https://s4-8003.nuage-peda.fr/slam/uploads/fichiers/" + response3.body().getNom()).into(imageViewFichier1);
                            } else if (finalI == 1) {
                                Picasso.get().load("https://s4-8003.nuage-peda.fr/slam/uploads/fichiers/" + response3.body().getNom()).into(imageViewFichier2);
                            } else if (finalI == 2) {
                                Picasso.get().load("https://s4-8003.nuage-peda.fr/slam/uploads/fichiers/" + response3.body().getNom()).into(imageviewFichier3);
                            }
                        }

                        @Override
                        public void onFailure(Call<FichierResponse> call, Throwable t) {
                            Toast.makeText(AnnonceAfficheActivity.this, "Erreur lors de l'appel", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Annonce> call, Throwable t) {
                Toast.makeText(AnnonceAfficheActivity.this, "Erreur lors de l'appel", Toast.LENGTH_LONG).show();
            }
        });
    }
}