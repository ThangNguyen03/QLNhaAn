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
import Model.ThucDon;
import spiner.IFirebaseLoadDone;

public class ThucDonBanAn1 extends AppCompatActivity implements IFirebaseLoadDone {
TextView txtTenBanAn;
TextView txtThemTDBA;
    private FirebaseRecyclerOptions<Ban> options;
    DatabaseReference Ref;
    RecyclerView recTDBA;
    TextView txtTenTDBA,txtGiaTDBA;
    Button btnThemTDBA;
    EditText edtSoLuongTDBA;
    private FirebaseRecyclerAdapter<Ban,MyViewHolder> adapter;
    IFirebaseLoadDone iFirebaseLoadDone;
    DatabaseReference Ref1;
    List<ThucDon> thucDons;
    boolean chk=true;
    BottomSheetDialog bottomSheetDialog;
Spinner spTenTDBanAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don_ban_an);
        txtTenBanAn=findViewById(R.id.txtTenBanAn);
        edtSoLuongTDBA=findViewById(R.id.edtSoLuongTDBA);
        txtThemTDBA=findViewById(R.id.txtThemTDBA);
        spTenTDBanAn=findViewById(R.id.spTenTDBanAn);
        bottomSheetDialog=new BottomSheetDialog(this);



        View bottom_dialog=getLayoutInflater().inflate(R.layout.dialogthemtdban,null);
        txtTenTDBA=bottom_dialog.findViewById(R.id.txtTenTDBA);
        txtGiaTDBA=bottom_dialog.findViewById(R.id.txtGiaTDBA);
        edtSoLuongTDBA=bottom_dialog.findViewById(R.id.edtSoLuongTDBA);
        btnThemTDBA=bottom_dialog.findViewById(R.id.btnThemTDBA);
        String tenban=getIntent().getStringExtra("tenban");
        btnThemTDBA.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
        Ref=FirebaseDatabase.getInstance().getReference().child(tenban);
        int giatd=Integer.parseInt(txtGiaTDBA.getText().toString().trim());
        String tentd=txtTenTDBA.getText().toString().trim();
        int sltd=Integer.parseInt(edtSoLuongTDBA.getText().toString().trim());
        int tongtien=giatd*sltd;
        String key=Ref.push().getKey();
        Ref.child(key).child("tentd").setValue(tentd);
        Ref.child(key).child("giatd").setValue(giatd);
        Ref.child(key).child("sltd").setValue(sltd);
        Ref.child(key).child("tongtientd").setValue(tongtien);
        Toast.makeText(ThucDonBanAn1.this,"Thêm thuc don thành công", Toast.LENGTH_SHORT).show();
    }

});
        bottomSheetDialog.setContentView(bottom_dialog);
        spTenTDBanAn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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





        recTDBA=findViewById(R.id.recTDBA);


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

                holder.txtTenThucDon.setText(model.getTenban());

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





    }

    @Override
    public void onFirebaseLoadSucces(List<ThucDon> thucDonList) {
        thucDons=thucDonList;
        List<String > tentd=new ArrayList<>();
        for(ThucDon thucDon:thucDonList)
            tentd.add(thucDon.getTentd());
        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tentd);
        spTenTDBanAn.setAdapter(arrayAdapter1);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {

    }
}