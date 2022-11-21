package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.NhanVien;

public class QuanLyNhanVien extends AppCompatActivity {
List<NhanVien> dsNV;
AdapterDSNV adapterDSNV;
RecyclerView recNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        recNV=findViewById(R.id.recNV);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recNV.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recNV.addItemDecoration(dividerItemDecoration);
        dsNV=new ArrayList<NhanVien>();
        adapterDSNV=new AdapterDSNV(dsNV, new AdapterDSNV.IClick() {
            @Override
            public void onclickSuaNV(NhanVien nhanVien) {
                openDialogItem(nhanVien);
            }
        });
        recNV.setAdapter(adapterDSNV);
        HienThiListNV();
        registerForContextMenu(recNV);
    }
    private void HienThiListNV(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("employees");
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        NhanVien nhanVien=snapshot.getValue(NhanVien.class);
        if(nhanVien!=null){
        dsNV.add(nhanVien);
        adapterDSNV.notifyDataSetChanged();
            }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NhanVien nhanVien=snapshot.getValue(NhanVien.class);
                if(nhanVien==null||dsNV==null||dsNV.isEmpty()){
                    return;
                }
                for(int i=0;i<dsNV.size();i++){
                    if(nhanVien.getEmail()==dsNV.get(i).getEmail()){
                        dsNV.set(i,nhanVien);
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

   private void openDialogItem(NhanVien nhanVien){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogsuanv);
        Window window=dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

       EditText edttennv,edtns,edtsdt,edtemail,edtmk;
       RadioGroup radioGroup;
       RadioButton radNam,radNu;
       Button btnSuaNV,btnHuy;
       edttennv=dialog.findViewById(R.id.edttennv);
       edtns=dialog.findViewById(R.id.edtngaysinh);
       edtemail=dialog.findViewById(R.id.edtemail);
       edtmk=dialog.findViewById(R.id.edtpass);
       edtsdt=dialog.findViewById(R.id.edtsdt);
       radioGroup=dialog.findViewById(R.id.radioG);
       radNam=dialog.findViewById(R.id.radNam);
       radNu=dialog.findViewById(R.id.radNu);
       btnSuaNV=dialog.findViewById(R.id.btnSuaNV);
       btnHuy=dialog.findViewById(R.id.btnHuy);

       edttennv.setText(nhanVien.getTennv());
       edtns.setText(nhanVien.getNgaysinh());
       edtsdt.setText(nhanVien.getSdt());
       edtemail.setText(nhanVien.getEmail());
       edtmk.setText(nhanVien.getMatkhau());
       int chkrad=radioGroup.getCheckedRadioButtonId();
       String gt;
       if(nhanVien.getGioitinh()=="Nam"){
          radNam.isChecked();
       }else{
           radNu.isChecked();
       }
       btnHuy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.dismiss();
           }
       });
       btnSuaNV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FirebaseDatabase database=FirebaseDatabase.getInstance();
               DatabaseReference myref=database.getReference("employees");
               String newTenNV=edttennv.getText().toString().trim();
               nhanVien.setTennv(newTenNV);
               int chkrad=radioGroup.getCheckedRadioButtonId();
               String gt;
               if(chkrad==R.id.radNam){
                   gt="Nam";
               }else{
                   gt="Nữ";
               }
               nhanVien.setGioitinh(gt);
               String newNS=edtns.getText().toString().trim();
               nhanVien.setNgaysinh(newNS);
               String newSDT=edtsdt.getText().toString().trim();
               nhanVien.setSdt(newSDT);
               String newEmail=edtemail.getText().toString().trim();
               nhanVien.setEmail(newEmail);

               String newMK=edtmk.getText().toString().trim();
               nhanVien.setMatkhau(newMK);

               myref.child(String.valueOf(nhanVien.getEmail())).updateChildren(nhanVien.toMap(), new DatabaseReference.CompletionListener() {
                   @Override
                   public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                       Toast.makeText(QuanLyNhanVien.this,"Sửa nhân viên thành công",Toast.LENGTH_SHORT).show();
                       dialog.dismiss();
                   }
               });
           }
       });
        dialog.show();
   }
}