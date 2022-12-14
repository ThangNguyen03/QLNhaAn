package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import Model.NhanVien;
import Model.TaiKhoan;

public class DangKy extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button btnDangKy,btnHuy,btnXem;
    EditText edtemail,edtpass,edtconfirmpass,edttennv,edtsdt,edtngaysinh;
    RadioButton radNam,radNu;
    RadioGroup radioG;
    TextView txtGT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDangKy=findViewById(R.id.btnDangKy);
        btnHuy=findViewById(R.id.btnHuy);

        edtemail=findViewById(R.id.edtemail);
        edtpass=findViewById(R.id.edtpass);

        mAuth = FirebaseAuth.getInstance();
        edtconfirmpass=findViewById(R.id.edtconfirmpass);


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass=edtpass.getText().toString().trim();
                String email=edtemail.getText().toString().trim();
                String cfpass=edtconfirmpass.getText().toString().trim();


                if(email.isEmpty()){
                    edtemail.setError("Email không được để trống");
                    edtemail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtemail.setError("Email không đúng định dạng");
                    edtemail.requestFocus();
                    return;
                }
                if(pass.length()<6){
                    edtpass.setError("Mât khẩu phải lớn hơn 6 ký tự");
                    edtpass.requestFocus();
                    return;
                }
                if(!cfpass.equals(pass)){
                    edtconfirmpass.getText().clear();
                    edtconfirmpass.setError("Xác nhận mật khẩu không đúng");
                    edtconfirmpass.requestFocus();
                    return;
                }


                    mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    TaiKhoan taiKhoan=new TaiKhoan(email,pass);
                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(taiKhoan).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(DangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                                    }else {
                                                        Toast.makeText(DangKy.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }else{
                                    Toast.makeText(DangKy.this,"Email đã tồn tại",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
//    private  void onclickDK(NhanVien nhanVien){FirebaseDatabase database=FirebaseDatabase.getInstance();
//        DatabaseReference myref=database.getReference("employees");
//        String  path=String.valueOf(nhanVien.getEmail());
//        myref.child(path).setValue(nhanVien, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(DangKy.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}