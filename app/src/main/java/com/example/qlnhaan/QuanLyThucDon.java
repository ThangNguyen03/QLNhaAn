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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import Model.NhanVien;
import Model.ThucDon;

public class QuanLyThucDon extends AppCompatActivity {
TextView txtThemTD;
//    FirebaseDatabase mDatabase;
//    DatabaseReference myRef;
//    FirebaseStorage mStorage;
    RecyclerView recTD;
    AdapterThucDon adapterThucDon;
    List<ThucDon> dsThucDon;
    AdapterUpdateTD adapterUpdateTD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_thuc_don);
        txtThemTD=findViewById(R.id.txtThemTD);
        recTD=findViewById(R.id.recTD);

//        mDatabase=FirebaseDatabase.getInstance();
//        myRef=mDatabase.getReference().child("ThucDon");
//        mStorage=FirebaseStorage.getInstance();


        recTD.setLayoutManager(new LinearLayoutManager(this));


//        dsThucDon=new ArrayList<ThucDon>();
//        adapterThucDon=new AdapterThucDon(QuanLyThucDon.this,dsThucDon);
//        recTD.setAdapter(adapterThucDon);

       //HienThiListNV();
        FirebaseRecyclerOptions<ThucDon> options=new FirebaseRecyclerOptions.Builder<ThucDon>().setQuery(FirebaseDatabase.getInstance().
                getReference().child("ThucDon"),ThucDon.class).build();
                adapterUpdateTD=new AdapterUpdateTD(options,this);
                recTD.setAdapter(adapterUpdateTD);
             txtThemTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanLyThucDon.this,ThemTD.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterUpdateTD.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterUpdateTD.stopListening();
    }

//    private void HienThiListNV(){
//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        DatabaseReference myref=database.getReference("ThucDon");
//        myref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                ThucDon thucDon=snapshot.getValue(ThucDon.class);
//                if(thucDon!=null){
//                    dsThucDon.add(thucDon);
//                    adapterThucDon.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}