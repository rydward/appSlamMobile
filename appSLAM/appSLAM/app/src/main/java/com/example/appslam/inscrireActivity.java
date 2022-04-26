package com.example.appslam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appslam.chat.activityMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inscrireActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire);
        editTextEmail = findViewById(R.id.editTextMail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextPasswordConfirm);
    }

    public void onConnectButtonInscrire(View v){
        Intent connexionPage = new Intent(this, connectActivity.class);
        startActivity(connexionPage);
    }

    public void onInscrireButtonInscrire(View v){
        userSignUp();
    }
    private void userSignUp(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordConfirm = editTextConfirmPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Veuillez rentrer un email");
            editTextEmail.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextEmail.setError("Veuillez rentrer un email valide");
                editTextEmail.requestFocus();
                return;
            }
        else if(password.isEmpty()){
            editTextPassword.setError("Veuillez rentrer un mot de passe");
            editTextPassword.requestFocus();
            return;
        }
        else if(passwordConfirm.isEmpty()){
            editTextConfirmPassword.setError("Veuillez confirmer le mot de passe");
            editTextConfirmPassword.requestFocus();
            return;
        }
        else if(!passwordConfirm.matches(password)){
            editTextConfirmPassword.setError("Les mots de passe ne correspondent pas");
            editTextConfirmPassword.requestFocus();
            return;
        }
        else{
            String[] roles = {"ROLE_USER"};
            SignUpSend signUpSend = new SignUpSend(email, roles, password);
            Call<SignUpResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .userCreate(signUpSend);
            call.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if(response.body().getEmail() != null){
                        Toast.makeText(inscrireActivity.this, "Utilisateur créé, vous pouvez vous connecter", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(inscrireActivity.this, "Erreur lors de l'ajout de l'utilisateur", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    Toast.makeText(inscrireActivity.this, "Erreur lors de l'appel", Toast.LENGTH_LONG).show();
                }
            });
            }
        }

    public void onEnvoyerMessage(View v){
        Intent envoiMessage = new Intent(this, activityMessage.class);
        startActivity(envoiMessage);
    }

    }
