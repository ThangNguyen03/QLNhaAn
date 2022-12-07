package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterTKDT;
import Model.ThongKeDoanhThu;

public class QuanLyThongKe extends AppCompatActivity {
    RecyclerView recTK;
    List<ThongKeDoanhThu> dsTKDT;
    AdapterTKDT adapterTKDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_thong_ke);
        recTK = findViewById(R.id.recTK);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recTK.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recTK.addItemDecoration(dividerItemDecoration);
        dsTKDT=new ArrayList<ThongKeDoanhThu>();
        adapterTKDT=new AdapterTKDT(dsTKDT);
        recTK.setAdapter(adapterTKDT);
        HienThiListTKDT();
    }
    private void HienThiListTKDT(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("ThongKe");
        myref.addChildEventListener(new ChildEventListener() {
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