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

    TextView txtTenThucDon1,txtGiaThucDon1,txtSoLuong1,txtTongTien1;
    Button btnXoaTDBA1;
    TextView txtTrangThaiTT,txtNgayTT,txtTenBanTT,txtTongTienTT;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTenBA=itemView.findViewById(R.id.txtTenBan);

        btnXoaBan=itemView.findViewById(R.id.btnxoaBan);
        btnSuaBan=itemView.findViewById(R.id.btnSuaBan);



        txtTenThucDon1=itemView.findViewById(R.id.txtTenThucDon1);
        txtGiaThucDon1=itemView.findViewById(R.id.txtGiaThucDon1);
        txtSoLuong1=itemView.findViewById(R.id.txtSoLuong1);
        txtTongTien1=itemView.findViewById(R.id.txtTongTien1);
        btnXoaTDBA1=itemView.findViewById(R.id.btnXoaTDBA1);

        txtTenBanTT=itemView.findViewById(R.id.txtTenBanTT);
        txtNgayTT=itemView.findViewById(R.id.txtNgayTT);
        txtTrangThaiTT=itemView.findViewById(R.id.txtTrangThaiTT);
        txtTongTienTT=itemView.findViewById(R.id.txtTongTienTT);

        view=itemView;
    }
}
