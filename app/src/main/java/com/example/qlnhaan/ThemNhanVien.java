package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.NhanVien;

public class ThemNhanVien extends AppCompatActivity {
    Button btnThem,btnHuy;
    EditText edtemail,edtpass,edttennv,edtsdt,edtngaysinh,edtmanv;
    RadioButton radNam,radNu;
    RadioGroup radioG;
    TextView txtGT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        edtmanv=findViewById(R.id.edtmanv);
        btnThem=findViewById(R.id.btnThem);
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
        int chkrad=radioG.getCheckedRadioButtonId();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gt;
                if(radNam.isChecked()){
                    gt="Nam";
                }
               else
                {
                    gt="N???";
                }

                int manv=Integer.parseInt(edtmanv.getText().toString().trim());
                String tennv=edttennv.getText().toString().trim();
                String ngaysinh=edtngaysinh.getText().toString().trim();
                String sdt=edtsdt.getText().toString().trim();
                String pass=edtpass.getText().toString().trim();
                String email=edtemail.getText().toString().trim();
                if(tennv.isEmpty()){
                    edttennv.setError("T??n kh??ng ???????c ????? tr???ng");
                    edttennv.requestFocus();
                    return;
                }
                if(!(radNam.isChecked()||radNu.isChecked())){
                    txtGT.setError("Gi???i t??nh kh??ng ???????c ????? tr???ng");
                    radNu.requestFocus();
                    return;
                }
                if(ngaysinh.isEmpty()){
                    edtngaysinh.setError("Ng??y sinh kh??ng ???????c ????? tr???ng");
                    edtngaysinh.requestFocus();
                    return;
                }
                if(sdt.isEmpty()){
                    edtsdt.setError("S??? ??i???n tho???i kh??ng ???????c ????? tr???ng");
                    edtsdt.requestFocus();
                    return;
                }

                if(email.isEmpty()){
                    edtemail.setError("Email kh??ng ???????c ????? tr???ng");
                    edtemail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtemail.setError("Email kh??ng ????ng ?????nh d???ng");
                    edtemail.requestFocus();
                    return;
                }
                if(pass.length()<6){
                    edtpass.setError("M??t kh???u ph???i l???n h??n 6 k?? t???");
                    edtpass.requestFocus();
                    return;
                }
                NhanVien nhanVien=new NhanVien(manv,tennv,gt,ngaysinh,sdt,email,pass);
                AddNV(nhanVien);
            }
        });


    }
    private void  AddNV(NhanVien nhanVien){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("employees");
        String  path=String.valueOf(nhanVien.getManv());
        myref.child(path).setValue(nhanVien, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(ThemNhanVien.this,"Th??m nh??n vi??n th??nh c??ng",Toast.LENGTH_SHORT).show();
            }
        });
    }
}