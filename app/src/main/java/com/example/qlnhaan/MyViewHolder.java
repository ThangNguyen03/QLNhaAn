package com.example.qlnhaan;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder {
TextView txtTenBA;
View view;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTenBA=itemView.findViewById(R.id.txtTenBan);
        view=itemView;
    }
}
