package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhap extends AppCompatActivity {
    Button btnDangNhap,btnHuy;
    EditText edtemail,edtpass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtemail=findViewById(R.id.edtemail);
        edtpass=findViewById(R.id.edtpass);
        btnDangNhap=findViewById(R.id.btnDangNhap);
        btnHuy=findViewById(R.id.btnHuy);
        mAuth = FirebaseAuth.getInstance();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=edtpass.getText().toString().trim();
                String email=edtemail.getText().toString().trim();
                if(email.isEmpty()){
                    edtemail.setError("Email không được để trống");
                    edtemail.requestFocus();
                    return;
                }
                if(pass.isEmpty()){
                    edtpass.setError("Password không được để trống");
                    edtpass.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(DangNhap.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(DangNhap.this,TrangChu.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(DangNhap.this,"Email hoặc Pasword không hợp lệ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }}