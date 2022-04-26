package com.example.appslam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class connectActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        editTextEmail = findViewById(R.id.editTextPersonEmail);
        editTextPassword = findViewById(R.id.editTextPersonPassword);
        textView = findViewById(R.id.textView3);
    }

    public void onConnectButtonConnect(View v){
        userLogin();
    }

    public void onInscrireButtonConnect(View v){
        Intent registerPage = new Intent(this, inscrireActivity.class);
        startActivity(registerPage);
    }

    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Veuillez rentrer un email");
            editTextEmail.requestFocus();
            return;
        }
        else if(password.isEmpty()){
            editTextPassword.setError("Veuillez rentrer un mot de passe");
            editTextPassword.requestFocus();
            return;
        }
        else{
            LoginSend loginSend = new LoginSend(email, password);
            Call<LoginResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .userLogin(loginSend);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.body() == null){
                        Toast.makeText(connectActivity.this, "Utilisateur non trouvé", Toast.LENGTH_LONG).show();
                    }
                    else{
                        System.out.println(response.body().getToken());
                        onNextPage(response.body().getData().utilisateur.toString());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(connectActivity.this, "Erreur lors de l'appel", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void onNextPage(String utilisateurId){
        Intent accueilPage= new Intent(this, accueilActivity.class);
        accueilPage.putExtra("utilisateurId", utilisateurId);
        startActivity(accueilPage);
        System.out.println(utilisateurId);
    }
}