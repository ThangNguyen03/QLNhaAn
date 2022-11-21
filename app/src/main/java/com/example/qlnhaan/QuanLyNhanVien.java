package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.NhanVien;

public class QuanLyNhanVien extends AppCompatActivity {
List<NhanVien> dsNV;
AdapterDSNV adapterDSNV;
//ListView litnv;
RecyclerView recNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        //litnv=findViewById(R.id.recNV);
        recNV=findViewById(R.id.recNV);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recNV.setLayoutManager(linearLayoutManager);
       DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recNV.addItemDecoration(dividerItemDecoration);
        dsNV=new ArrayList<NhanVien>();
        adapterDSNV=new AdapterDSNV(dsNV);
        recNV.setAdapter(adapterDSNV);

        HienThiListNV();
    }
    private void HienThiListNV(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("employees");
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        NhanVien nhanVien=snapshot.getValue(NhanVien.class);
        if(nhanVien!=null){
        dsNV.add(nhanVien);
        adapterDSNV.notifyDataSetChanged();
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