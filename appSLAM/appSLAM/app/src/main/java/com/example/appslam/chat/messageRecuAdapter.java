package com.example.appslam.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appslam.R;
import com.example.appslam.annonce.annoncesItem;

import java.util.List;

public class messageRecuAdapter extends BaseAdapter {

    private Context context;
    private List<Chat> listMessageRecu;
    private LayoutInflater inflater;
    private int count;

    public messageRecuAdapter(Context context, List<Chat> listMessageRecu){
        this.context = context;
        this.listMessageRecu = listMessageRecu;
        this.inflater = LayoutInflater.from(context);
    }

    public void count(int count){
        this.count = count;
    }

    @Override
    public int getCount() {
        System.out.println(listMessageRecu.size());
        return listMessageRecu.size(); }

    @Override
    public Chat getItem(int position) {
        return listMessageRecu.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_item_message_envoi, null);

        Chat currentItem = getItem(i);
        String itemContenu = currentItem.getContenu();
        String itemNom = currentItem.getUserrecoi();

        TextView destinataire = view.findViewById(R.id.Destinataire);
        destinataire.setText("Message de : "+ itemNom);
        TextView contenu = view.findViewById(R.id.message);
        contenu.setText(itemContenu);

        return view;
    }
}
