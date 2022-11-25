package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.Ban;
import Model.BanAn1;
import Model.ThucDon;
import spiner.IFirebaseLoadDone;

public class BanSo1 extends AppCompatActivity implements IFirebaseLoadDone {
    TextView txtTenBanAn1;
    private FirebaseRecyclerOptions<BanAn1> options;
    DatabaseReference Ref;
    RecyclerView recTDBA1;
    TextView txtTenTDBA,txtGiaTDBA;
    Button btnThemTDBA;
    EditText edtSoLuongTDBA;
    private FirebaseRecyclerAdapter<BanAn1,MyViewHolder> adapter;
    IFirebaseLoadDone iFirebaseLoadDone;
    DatabaseReference Ref1,Ref2;
    List<ThucDon> thucDons;
    boolean chk=true;
    BottomSheetDialog bottomSheetDialog;
    Spinner spTenTDBanAn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_so1);
        txtTenBanAn1=findViewById(R.id.txtTenBanAn1);
        spTenTDBanAn1=findViewById(R.id.spTenTDBanAn1);
        recTDBA1=findViewById(R.id.recTDBA1);

        recTDBA1.setHasFixedSize(true);
        recTDBA1.setLayoutManager(new LinearLayoutManager(this));

        bottomSheetDialog=new BottomSheetDialog(this);
        View bottom_dialog=getLayoutInflater().inflate(R.layout.dialogthemtdban1,null);
        txtTenTDBA=bottom_dialog.findViewById(R.id.txtTenTDBA);
        txtGiaTDBA=bottom_dialog.findViewById(R.id.txtGiaTDBA);
        edtSoLuongTDBA=bottom_dialog.findViewById(R.id.edtSoLuongTDBA);
        btnThemTDBA=bottom_dialog.findViewById(R.id.btnThemTDBA);
        btnThemTDBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ref= FirebaseDatabase.getInstance().getReference().child("BanAn1");
                int giatd=Integer.parseInt(txtGiaTDBA.getText().toString().trim());
                String tentd=txtTenTDBA.getText().toString().trim();
                int sltd=Integer.parseInt(edtSoLuongTDBA.getText().toString().trim());
                int tongtientd=giatd*sltd;
                String key=Ref.push().getKey();
                Ref.child(key).child("tentd").setValue(tentd);
                Ref.child(key).child("giatd").setValue(giatd);
                Ref.child(key).child("sltd").setValue(sltd);
                Ref.child(key).child("tongtientd").setValue(tongtientd);
                Toast.makeText(BanSo1.this,"Thêm thuc don thành công", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

        });


        bottomSheetDialog.setContentView(bottom_dialog);
        spTenTDBanAn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!chk){
                    ThucDon thucDon=thucDons.get(i);
                    txtTenTDBA.setText(thucDon.getTentd());
                    txtGiaTDBA.setText(thucDon.getGiatd());
                    bottomSheetDialog.show();
                }else{
                    chk=false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Ref1=FirebaseDatabase.getInstance().getReference("ThucDon");
        iFirebaseLoadDone= (IFirebaseLoadDone) this;
        Ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ThucDon> thucDonList=new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    thucDonList.add(dataSnapshot.getValue(ThucDon.class));
                }
                iFirebaseLoadDone.onFirebaseLoadSucces(thucDonList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iFirebaseLoadDone.onFirebaseLoadFailed(error.getMessage());
            }
        });



        Ref=FirebaseDatabase.getInstance().getReference().child("BanAn1");
        options=new FirebaseRecyclerOptions.Builder<BanAn1>().setQuery(Ref,BanAn1.class).build();
        adapter=new FirebaseRecyclerAdapter<BanAn1, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull BanAn1 model) {
                holder.txtTenThucDon1.setText(model.getTentd());
                holder.txtGiaThucDon1.setText(model.getGiatd()+"");
                holder.txtSoLuong1.setText(model.getSltd()+"");
                holder.txtTongTien1.setText(model.getTongtientd()+"");
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listtdbanan1,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recTDBA1.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Ref2=FirebaseDatabase.getInstance().getReference().child("BanAn");
        String key=getIntent().getStringExtra("key");
        Ref2.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String ten=snapshot.child("tenban").getValue().toString();
                txtTenBanAn1.setText(ten);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onFirebaseLoadSucces(List<ThucDon> thucDonList) {
        thucDons=thucDonList;
        List<String > tentd=new ArrayList<>();
        for(ThucDon thucDon:thucDonList)
            tentd.add(thucDon.getTentd());
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tentd);
        spTenTDBanAn1.setAdapter(arrayAdapter1);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {

    }
}