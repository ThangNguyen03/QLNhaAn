package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Ban;
import Model.ThucDon;

public class QuanLyBanAn extends AppCompatActivity {
TextView txtThemBA;
RecyclerView recBan;
private  FirebaseRecyclerOptions<Ban> options;
    private DatabaseReference Ref;
private FirebaseRecyclerAdapter<Ban,MyViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_ban_an);
        txtThemBA=findViewById(R.id.txtThemBA);
        recBan=findViewById(R.id.redBan);

        recBan.setHasFixedSize(true);
        recBan.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
     recBan.setLayoutManager(gridLayoutManager);
        txtThemBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanLyBanAn.this,ThemBA.class);
                startActivity(intent);
            }
        });
        Ref=FirebaseDatabase.getInstance().getReference().child("BanAn");
        options=new FirebaseRecyclerOptions.Builder<Ban>().setQuery(Ref,Ban.class).build();
        adapter=new FirebaseRecyclerAdapter<Ban, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Ban model) {
                final String tenbanan=model.getTenban();
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
Intent intent=new Intent(getApplicationContext(),ThucDonBanAn.class);
intent.putExtra("tenban",tenbanan);
startActivity(intent);
                    }
                });

                holder.txtTenBA.setText(model.getTenban());
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listbanan,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recBan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}