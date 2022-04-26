package com.example.appslam;

import com.example.appslam.annonce.Annonce;
import com.example.appslam.annonce.AnnoncesResponse;
import com.example.appslam.chat.Chat;
import com.example.appslam.chat.MessagechatsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("authentication_token")
    Call<LoginResponse> userLogin(@Body LoginSend loginSend);

    @POST("users")
    Call<SignUpResponse> userCreate(@Body SignUpSend signUpSend);

    @GET("annonces")
    Call<AnnoncesResponse> getAnnonces();

    @GET("messagechats")
    Call<MessagechatsResponse> getMessages();

    @GET("messagechats/{id}")
    Call<Chat> getMessage(
            @Path("id") String id
    );

    @GET("utilisateurs/{id}")
    Call<Utilisateur> getUtilisateur(
            @Path("id") String id
    );

    @GET("annonces/{id}")
    Call<Annonce> getAnnonce(
        @Path("id") String id
    );

    @GET("types/{id}")
    Call<TypeResponse> getType(
            @Path("id") String id
    );

    @GET("fichiers/{id}")
    Call<FichierResponse> getFichier(
            @Path("id") String id
    );
}
