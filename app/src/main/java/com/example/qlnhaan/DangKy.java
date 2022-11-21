package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.regex.Pattern;

import Model.Admin;
import Model.NhanVien;

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
        edttennv=findViewById(R.id.edttennv);
        edtsdt=findViewById(R.id.edtsdt);
        edtngaysinh=findViewById(R.id.edtngaysinh);
        edtemail=findViewById(R.id.edtemail);
        edtpass=findViewById(R.id.edtpass);
        radNam=findViewById(R.id.radNam);
        radNu=findViewById(R.id.radNu);
        radioG=findViewById(R.id.radioG);
        txtGT=findViewById(R.id.txtGT);
        mAuth = FirebaseAuth.getInstance();
        edtconfirmpass=findViewById(R.id.edtconfirmpass);


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tennv=edttennv.getText().toString().trim();
                String ngaysinh=edtngaysinh.getText().toString().trim();
                String sdt=edtsdt.getText().toString().trim();
                String pass=edtpass.getText().toString().trim();
                String email=edtemail.getText().toString().trim();
                String cfpass=edtconfirmpass.getText().toString().trim();

                if(tennv.isEmpty()){
                    edttennv.setError("Tên không được để trống");
                    edttennv.requestFocus();
                    return;
                }
                if(!(radNam.isChecked()||radNu.isChecked())){
                    txtGT.setError("Giới tính không được để trống");
                    radNu.requestFocus();
                    return;
                }
                if(ngaysinh.isEmpty()){
                    edtngaysinh.setError("Ngày sinh không được để trống");
                    edtngaysinh.requestFocus();
                    return;
                }
                if(sdt.isEmpty()){
                    edtsdt.setError("Số điện thoại không được để trống");
                    edtsdt.requestFocus();
                    return;
                }

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
                int chkrad=radioG.getCheckedRadioButtonId();
                String gt;
                if(chkrad==R.id.radNam){
                    gt="Nam";
                }else{
                    gt="Nữ";
                }

                    mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    NhanVien nhanVien=new NhanVien(tennv,gt,ngaysinh,sdt,email,pass);
                                    FirebaseDatabase.getInstance().getReference("employees")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(nhanVien).addOnCompleteListener(new OnCompleteListener<Void>() {
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