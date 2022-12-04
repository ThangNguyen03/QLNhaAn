package com.example.qlnhaan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ThemTD extends AppCompatActivity {
    FirebaseDatabase mDatabase;
DatabaseReference myRef;
FirebaseStorage mStorage;
ImageButton imageButton;
EditText edtTenTD,edtMaTD,edtGia;
Button btnThemTD,btnHuy;
public  static  final int Gallery_Code=1;
Uri imageUrl=null;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_td);
        imageButton=findViewById(R.id.imageButton);
        edtTenTD=findViewById(R.id.edtTenTD);
        edtMaTD=findViewById(R.id.edtMaTD);
        edtGia=findViewById(R.id.edtGia);
        btnThemTD=findViewById(R.id.btnThemTD);
        btnHuy=findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View view) {
        onBackPressed();
    }
});
        mDatabase=FirebaseDatabase.getInstance();
        myRef=mDatabase.getReference().child("ThucDon");
        mStorage=FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageUrl=data.getData();
            imageButton.setImageURI(imageUrl);

        }
        btnThemTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String matd=edtMaTD.getText().toString().trim();
               final String tentd=edtTenTD.getText().toString().trim();
                final String giatd=edtGia.getText().toString().trim();
                if(matd.isEmpty()){
                    edtMaTD.setError("Tên không được để trống");
                    edtMaTD.requestFocus();
                    return;
                }else
                if(tentd.isEmpty()){
                    edtTenTD.setError("Tên không được để trống");
                    edtTenTD.requestFocus();
                    return;
                }else
                if(giatd.isEmpty()){
                    edtGia.setError("Tên không được để trống");
                    edtGia.requestFocus();
                    return;
                }else
               {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ThemTD.this);
                    alertDialogBuilder.setTitle("Vui lòng chọn ảnh");
                   alertDialogBuilder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           Toast.makeText(ThemTD.this,"Bạn đã click vào nút không đồng ý",Toast.LENGTH_SHORT).show();
                       }
                   });
                   AlertDialog alertDialog = alertDialogBuilder.create();
                   alertDialog.show();
                }
                if(!(tentd.isEmpty()&&tentd.isEmpty()&&imageUrl!=null)){
                    progressDialog.setTitle("Loading...");
                    progressDialog.show();
                    StorageReference filepath=mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t=task.getResult().toString();
                                    DatabaseReference newPost=myRef.push();
                                    newPost.child("matd").setValue(matd);
                                    newPost.child("tentd").setValue(tentd);
                                    newPost.child("giatd").setValue(giatd);
                                    newPost.child("anhtd").setValue(task.getResult().toString());
                                    progressDialog.dismiss();
                                    Toast.makeText(ThemTD.this,"Thêm thực đơn thành công", Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent(ThemTD.this,QuanLyThucDon.class);
//                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });

    }
}