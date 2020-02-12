package com.example.icitieslxnp;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;


class SimpleViewHolder extends RecyclerView.ViewHolder {

    TextView[] view = new TextView[2];


    public SimpleViewHolder(@NonNull View itemView, int[] to) {
        super(itemView);

        view[0] = itemView.findViewById(to[0]);
        view[1] = itemView.findViewById(to[1]);

    }

    public void bind(int pos,String dato){

        view[pos].setText(dato);
    }




}
