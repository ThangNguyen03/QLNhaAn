package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.List;

import Model.Ban;

public class ThucDonBanAn extends AppCompatActivity {
TextView txtTenBanAn;
TextView txtThemTDBA;
    private FirebaseRecyclerOptions<Ban> options;
    DatabaseReference Ref;
    RecyclerView recTDBA;
    Ban ban;
    Spinner spTenTD,spGiaTD;
    EditText edtSoLuongTDBA;
    List<String > spten;
    List<Integer> spgia;
    ArrayAdapter<String> arrayAdapter;
    private FirebaseRecyclerAdapter<Ban,MyViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don_ban_an);
        txtTenBanAn=findViewById(R.id.txtTenBanAn);
        spTenTD=findViewById(R.id.spTenTD);
        spGiaTD=findViewById(R.id.spGiaTD);
        edtSoLuongTDBA=findViewById(R.id.edtSoLuongTDBA);
        txtThemTDBA=findViewById(R.id.txtThemTDBA);
        Ref=FirebaseDatabase.getInstance().getReference().child("ThucDon");

        txtThemTDBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus=DialogPlus.newDialog(ThucDonBanAn.this)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialogthemthucdonbanan))
                        .setExpanded(false).create();
                View holderView=dialogPlus.getHolderView();

                Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String spinerName=snapshot.child("tentd").getValue(String.class);
                        int spinerGia=snapshot.child("giatd").getValue(int.class);
                        spten.add(spinerName);
                        spgia.add(spinerGia);
                        ArrayAdapter arrayAdapter1=new ArrayAdapter<>(ThucDonBanAn.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spgia);
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(ThucDonBanAn.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spten);
                   arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                        arrayAdapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                        spTenTD.setAdapter(arrayAdapter);
                        spGiaTD.setAdapter(arrayAdapter1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                EditText edttenban=holderView.findViewById(R.id.edttenBan);

                Button btnSuaBan=holderView.findViewById(R.id.btnSuaBan);
                Button btnHuy=holderView.findViewById(R.id.btnHuy);

                edttenban.setText(ban.getTenban());
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPlus.dismiss();
                    }
                });
                btnSuaBan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                dialogPlus.show();
            }
        });
        recTDBA=findViewById(R.id.recTDBA);
        Ref= FirebaseDatabase.getInstance().getReference().child("BanAn");

        recTDBA.setHasFixedSize(true);
        recTDBA.setLayoutManager(new LinearLayoutManager(this));

        Ref=FirebaseDatabase.getInstance().getReference().child("BanAn");
        options=new FirebaseRecyclerOptions.Builder<Ban>().setQuery(Ref,Ban.class).build();
        adapter= new FirebaseRecyclerAdapter<Ban, MyViewHolder>(options) {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listthucdonbanan,parent,false);
                return new MyViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Ban model) {

                holder.txtTenThucDon.setText(model.getTentd());
            }
        };
        adapter.startListening();
        recTDBA.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        String key=getIntent().getStringExtra("key");
        Ref.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

           String ten=snapshot.child("tenban").getValue().toString();
           txtTenBanAn.setText(ten);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}