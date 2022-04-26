package com.example.appslam.annonce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appslam.R;
import com.example.appslam.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnoncesFragment extends Fragment {

    private ListView annoncesListView;
    private List<Annonce> annoncesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Call<AnnoncesResponse> call = RetrofitClient.getInstance().getApi().getAnnonces();

        call.enqueue(new Callback<AnnoncesResponse>() {
            @Override
            public void onResponse(Call<AnnoncesResponse> call, Response<AnnoncesResponse> response) {
                List<annoncesItem> annoncesItemsList = new ArrayList<>();
                for (Annonce annonce: response.body().annonces) {
                    annoncesItemsList.add(new annoncesItem(annonce.designation, annonce.description, annonce.id, annonce.prix));
                }
                annoncesListView = getView().findViewById(R.id.annonces_list_view);
                annoncesListView.setAdapter(new AnnonceItemAdapter(getContext(), annoncesItemsList));
            }

            @Override
            public void onFailure(Call<AnnoncesResponse> call, Throwable t) {

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_annonces, null);
    }

}