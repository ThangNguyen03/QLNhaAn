package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Ban;

public class ThemBA extends AppCompatActivity {
EditText edtTenBan,edtMaBan;
Button btnThemBan;
private DatabaseReference Ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ba);
        edtTenBan=findViewById(R.id.edtTenBab);
        edtMaBan=findViewById(R.id.edtMaBan);
        btnThemBan=findViewById(R.id.btnThemBan);
        Ref=FirebaseDatabase.getInstance().getReference().child("BanAn");
        btnThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maban=Integer.parseInt(edtMaBan.getText().toString().trim());
                String tenban=edtTenBan.getText().toString().trim();
                String key=Ref.push().getKey();
                Ref.child(key).child("maban").setValue(maban);
                Ref.child(key).child("tenban").setValue(tenban);
                Toast.makeText(ThemBA.this,"Thêm bàn ăn thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

}