package com.example.appslam.annonce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appslam.R;
import com.example.appslam.chat.Chat;

import java.util.List;

public class AnnonceItemAdapter extends BaseAdapter {

    private Context context;
    private List<annoncesItem> annoncesItemList;
    private LayoutInflater inflater;

    public AnnonceItemAdapter(Context context, List<annoncesItem> annoncesItemList){
        this.context = context;
        this.annoncesItemList = annoncesItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return annoncesItemList.size();
    }

    @Override
    public annoncesItem getItem(int position) {
        return annoncesItemList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_item, null);

        annoncesItem currentItem = getItem(i);
        String itemDesignation = currentItem.getDesignation();
        String itemDescription = currentItem.getDescription();
        Integer itemId = currentItem.getId();
        Integer itemPrix = currentItem.getPrix();

        TextView itemDesignationView = view.findViewById(R.id.item_designation);
        TextView itemDescriptionView = view.findViewById(R.id.item_description);
        TextView itemIdView = view.findViewById(R.id.item_id);
        TextView itemIdPrix = view.findViewById(R.id.item_prix);

        itemDescriptionView.setText(itemDescription);
        itemDesignationView.setText(itemDesignation);
        itemIdView.setText("ID : " + itemId.toString());
        itemIdPrix.setText((itemPrix.toString() + "€"));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnnonceAfficheActivity.class);
                intent.putExtra("idAnnonce", itemId.toString());
                context.startActivity(intent);
            }
        });

        return view;
    }
}
