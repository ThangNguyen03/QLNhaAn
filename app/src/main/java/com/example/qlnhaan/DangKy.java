package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import Model.Admin;

public class DangKy extends AppCompatActivity {
   // Adapter adapter;
    //List<Admin> dsAdim;
    private FirebaseAuth mAuth;
    Button btnDangKy,btnHuy,btnXem;
    EditText edtemail,edtpass,edtconfirmpass;
    //ListView litadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDangKy=findViewById(R.id.btnDangKy);
        btnHuy=findViewById(R.id.btnHuy);
       // btnXem=findViewById(R.id.btnXem);
        edtemail=findViewById(R.id.edtemail);
        edtpass=findViewById(R.id.edtpass);
        mAuth = FirebaseAuth.getInstance();
        edtconfirmpass=findViewById(R.id.edtconfirmpass);
        //litadmin=findViewById(R.id.litadmin);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=edtpass.getText().toString().trim();
                String email=edtemail.getText().toString().trim();
                String cfpass=edtconfirmpass.getText().toString().trim();
                if(pass.equals(cfpass)){
                    mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Admin admin=new Admin(email,pass);
                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(DangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                                    }else {
                                                        Toast.makeText(DangKy.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            }
                        });}else{
                    Toast.makeText(DangKy.this,"Xác nhận  mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    edtconfirmpass.getText().clear();
                }

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}