package com.example.qlnhaan;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder {
TextView txtTenBA;
View view;
Button btnXoaBan,btnSuaBan;
TextView txtTenThucDon,txtGiaThucDon,txtSoLuong,txtTongTien;
Button btnXoaTDBA,btnSuaTDBA;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTenBA=itemView.findViewById(R.id.txtTenBan);

        btnXoaBan=itemView.findViewById(R.id.btnxoaBan);
        btnSuaBan=itemView.findViewById(R.id.btnSuaBan);

        txtTenThucDon=itemView.findViewById(R.id.txtTenThucDon);
        txtGiaThucDon=itemView.findViewById(R.id.txtGiaThucDon);
        txtSoLuong=itemView.findViewById(R.id.txtSoLuong);
        txtTongTien=itemView.findViewById(R.id.txtTongTien);
        btnXoaTDBA=itemView.findViewById(R.id.btnXoaTDBA);
        btnSuaTDBA=itemView.findViewById(R.id.btnSuaTDBA);
        view=itemView;
    }
}
