package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.ContextMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Model.NhanVien;

public class QuanLyNhanVien extends AppCompatActivity {
List<NhanVien> dsNV;
AdapterDSNV adapterDSNV;
RecyclerView recNV;
TextView txtThemNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        recNV=findViewById(R.id.recNV);
        txtThemNV=findViewById(R.id.txtThemNV);

        txtThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuanLyNhanVien.this,ThemNhanVien.class);
                startActivity(intent);
            }
        });
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

            @Override
            public void onClickXoaNV(NhanVien nhanVien) {
            XoaNV(nhanVien);
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
                    if(nhanVien.getManv()==dsNV.get(i).getManv()){
                        dsNV.set(i,nhanVien);
                        break;
                    }
                }
                adapterDSNV.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                NhanVien nhanVien=snapshot.getValue(NhanVien.class);
                if(nhanVien==null||dsNV==null||dsNV.isEmpty()){
                    return;
                }
                for(int i=0;i<dsNV.size();i++){
                    if(nhanVien.getManv()==dsNV.get(i).getManv()){
                        dsNV.remove(dsNV.get(i));
                        break;
                    }
                }
                adapterDSNV.notifyDataSetChanged();
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
       TextView txtGT;
//Hien thi len dialog
       edttennv=dialog.findViewById(R.id.edttennv);
       edtns=dialog.findViewById(R.id.edtngaysinh);
       edtemail=dialog.findViewById(R.id.edtemail);
       edtmk=dialog.findViewById(R.id.edtpass);
       edtsdt=dialog.findViewById(R.id.edtsdt);
       radioGroup=dialog.findViewById(R.id.radioG);
       radNam=dialog.findViewById(R.id.radNam);
       radNu=dialog.findViewById(R.id.radNu);
       txtGT=findViewById(R.id.txtGT);
       btnSuaNV=dialog.findViewById(R.id.btnSuaNV);
       btnHuy=dialog.findViewById(R.id.btnHuy);

       edttennv.setText(nhanVien.getTennv());
       edtns.setText(nhanVien.getNgaysinh());
       edtsdt.setText(nhanVien.getSdt());
       edtemail.setText(nhanVien.getEmail());
       edtmk.setText(nhanVien.getMatkhau());
       int chkrad=radioGroup.getCheckedRadioButtonId();

       String gt;
       if(radNam.isChecked()){
           gt="Nam";
           nhanVien.setGioitinh(gt);
       }else{
           gt="Nữ";
           nhanVien.setGioitinh(gt);
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
               if(newTenNV.isEmpty()){
                   edttennv.setError("Tên không được để trống");
                   edttennv.requestFocus();
                   return;
               }
               if(!(radNam.isChecked()||radNu.isChecked())){
                   txtGT.setError("Giới tính không được để trống");
                   radNu.requestFocus();
                   return;
               }
               if(newNS.isEmpty()){
                   edtns.setError("Ngày sinh không được để trống");
                   edtns.requestFocus();
                   return;
               }
               if(newSDT.isEmpty()){
                   edtsdt.setError("Số điện thoại không được để trống");
                   edtsdt.requestFocus();
                   return;
               }

               if(newEmail.isEmpty()){
                   edtemail.setError("Email không được để trống");
                   edtemail.requestFocus();
                   return;
               }
               if(!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()){
                   edtemail.setError("Email không đúng định dạng");
                   edtemail.requestFocus();
                   return;
               }
               if(newMK.length()<6){
                   edtmk.setError("Mât khẩu phải lớn hơn 6 ký tự");
                   edtmk.requestFocus();
                   return;
               }
               myref.child(String.valueOf(nhanVien.getManv())).updateChildren(nhanVien.toMap(), new DatabaseReference.CompletionListener() {
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
private  void XoaNV(NhanVien nhanVien){
new AlertDialog.Builder(this)
        .setTitle(getString(R.string.app_name)).setMessage("Bạn có chắc chắn muốn xóa không ?")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myref=database.getReference("employees");
                myref.child(String.valueOf(nhanVien.getManv())).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(QuanLyNhanVien.this,"Xoá nhân viên thành công",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).setNegativeButton("Hủy",null).show();

}

}