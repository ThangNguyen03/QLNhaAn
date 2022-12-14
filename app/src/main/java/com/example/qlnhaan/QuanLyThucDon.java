package com.example.qlnhaan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


import Adapter.AdapterThucDon;
import Adapter.AdapterUpdateTD;
import Model.ThucDon;

public class QuanLyThucDon extends AppCompatActivity {
TextView txtThemTD;
//    FirebaseDatabase mDatabase;
//    DatabaseReference myRef;
//    FirebaseStorage mStorage;
    ThucDon td;
    RecyclerView recTD;
    AdapterThucDon adapterThucDon;
    List<ThucDon> dsThucDon;
    AdapterUpdateTD adapterUpdateTD;
    EditText edtTimTD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_thuc_don);
        txtThemTD=findViewById(R.id.txtThemTD);



        recTD=findViewById(R.id.recTD);

        recTD.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ThucDon> options=new FirebaseRecyclerOptions.Builder<ThucDon>()
                .setQuery(FirebaseDatabase.getInstance().
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

//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapterUpdateTD.stopListening();
//    }


}
