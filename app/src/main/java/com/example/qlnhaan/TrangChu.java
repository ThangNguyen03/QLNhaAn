package com.example.qlnhaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TrangChu extends AppCompatActivity {
TextView txtTKDT,txtTTNV,txtTTD,txtDSBA;
LinearLayout LTTNV,LTKDT,LTTD,LDSBA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        LTKDT=findViewById(R.id.LTKDT);
        LTTNV=findViewById(R.id.LTTNV);
        LTTD=findViewById(R.id.LTTD);
        LDSBA=findViewById(R.id.LDSBA);
        LTTNV=findViewById(R.id.LTTNV);
        LTTNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,QuanLyNhanVien.class);
                startActivity(intent);
            }
        });
        LTTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,QuanLyThucDon.class);
                startActivity(intent);
            }
        });
        LDSBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,QuanLyBanAn.class);
                startActivity(intent);
            }
        });
        LTKDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,QuanLyThongKe.class);
                startActivity(intent);
            }
        });
    }
}