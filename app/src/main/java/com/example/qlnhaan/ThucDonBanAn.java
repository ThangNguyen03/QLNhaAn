package com.example.qlnhaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ThucDonBanAn extends AppCompatActivity {
TextView txtTenBanAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don_ban_an);
        txtTenBanAn=findViewById(R.id.txtTenBanAn);
        String ten=getIntent().getStringExtra("tenban");
        txtTenBanAn.setText(ten);
    }
}