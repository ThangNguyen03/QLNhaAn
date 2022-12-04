package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Model.ThongKeDoanhThu;

public class TrangChu extends AppCompatActivity {
TextView txtXemTCTD,txtXemTCTK;
ImageView imageView;
LinearLayout LTTNV,LTKDT,LTTD,LDSBA;
    DatabaseReference Ref1;
    RecyclerView recTKNow;
    List<ThongKeDoanhThu> dsTKDT;
    AdapterTKDT adapterTKDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        LTKDT=findViewById(R.id.LTKDT);
        LTTNV=findViewById(R.id.LTTNV);
        LTTD=findViewById(R.id.LTTD);
        LDSBA=findViewById(R.id.LDSBA);
        LTTNV=findViewById(R.id.LTTNV);
        txtXemTCTD=findViewById(R.id.txtXemTCTD);
        txtXemTCTK=findViewById(R.id.txtXemTCTK);
        recTKNow=findViewById(R.id.recTKNow);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recTKNow.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recTKNow.addItemDecoration(dividerItemDecoration);
        dsTKDT=new ArrayList<ThongKeDoanhThu>();
        adapterTKDT=new AdapterTKDT(dsTKDT);
        recTKNow.setAdapter(adapterTKDT);

        HienThiListTKDT();

        imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,QuanLyThucDon.class);
                startActivity(intent);
            }
        });
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
        txtXemTCTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,QuanLyThucDon.class);
                startActivity(intent);
            }
        });
        txtXemTCTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,QuanLyThongKe.class);
                startActivity(intent);
            }
        });
    }

    private void HienThiListTKDT() {
        String date = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("ThongKe");
        Query query = myref.orderByChild("ngaynow").equalTo(date);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ThongKeDoanhThu thongKeDoanhThu=snapshot.getValue(ThongKeDoanhThu.class);
                if(thongKeDoanhThu!=null){
                    dsTKDT.add(thongKeDoanhThu);
                    adapterTKDT.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}