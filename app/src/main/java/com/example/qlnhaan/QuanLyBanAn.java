package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

import Model.Ban;

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
            protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Ban model) {
                final String key=getRef(position).getKey();
                final String ten=model.getTenban();
                registerForContextMenu(holder.view);
                holder.btnSuaBan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogPlus dialogPlus=DialogPlus.newDialog(QuanLyBanAn.this)
                                .setGravity(Gravity.CENTER)
                                .setMargin(50,0,50,0)
                                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialogsuaba))
                                .setExpanded(false).create();
                        View holderView=dialogPlus.getHolderView();

                        EditText edttenban=holderView.findViewById(R.id.edttenBan);

                        Button btnSuaBan=holderView.findViewById(R.id.btnSuaBan);
                        Button btnHuy=holderView.findViewById(R.id.btnHuy);

                        edttenban.setText(model.getTenban());
                        btnHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogPlus.dismiss();
                            }
                        });
                        btnSuaBan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Map<String,Object> map=new HashMap<>();
                                map.put("tenban",edttenban.getText().toString());
                                FirebaseDatabase.getInstance().getReference().child("BanAn")
                                        .child(getRef(position).getKey())
                                        .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                dialogPlus.dismiss();
                                            }
                                        });
                            }
                        });
                        dialogPlus.show();
                    }
                });

                holder.btnXoaBan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder dia=new AlertDialog.Builder(QuanLyBanAn.this).setTitle("Bạn có chắc chắn muốn xóa không?")
                                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseDatabase.getInstance().getReference().child("BanAn").child(getRef(position).getKey())
                                                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(QuanLyBanAn.this,"Xóa thực đơn thành công",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                });
                        dia.create().show();
                    }
                });

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(position==0){
                            Intent intent=new Intent(getApplicationContext(), BanSo1.class);
                            intent.putExtra("key",key);
                            intent.putExtra("tenban",ten);
                            startActivity(intent);
                        }

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