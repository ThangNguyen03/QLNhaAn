package Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlnhaan.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import Model.ThucDon;

public class AdapterUpdateTD extends FirebaseRecyclerAdapter<ThucDon,AdapterUpdateTD.ViewHolder>  {

    private Context context;
    public AdapterUpdateTD(@NonNull FirebaseRecyclerOptions<ThucDon> options,Context context) {

        super(options);
        this.context=context;


    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull ThucDon model) {

        holder.txtTenTD.setText(model.getTentd());
        holder.txtGiaTD.setText(model.getGiatd()+"VND");
        String imageUri;
        imageUri=model.getAnhtd();
        Picasso.get().load(imageUri).into(holder.imageView);

        holder.imageXoaTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dia=new AlertDialog.Builder(context).setTitle("Bạn có chắc chắn muốn xóa không?")
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("ThucDon").child(getRef(position).getKey())
                                        .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(context,"Xóa thực đơn thành công",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                dia.create().show();
            }
        });
        holder.imageSuaTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus=DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialogsuatd))
                        .setExpanded(false).create();
                View holderView=dialogPlus.getHolderView();

                EditText edttentd=holderView.findViewById(R.id.edttentd);
                EditText edtgiatd=holderView.findViewById(R.id.edtgiatd);

                Button btnSuaTD=holderView.findViewById(R.id.btnSuaTD);
                Button btnHuy=holderView.findViewById(R.id.btnHuy);

                edttentd.setText(model.getTentd());
                edtgiatd.setText(model.getGiatd());

        btnHuy.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         dialogPlus.dismiss();
        }
        });

                btnSuaTD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edttentd.getText().toString().isEmpty()){
                            edttentd.setError("Tên không được để trống");
                            edttentd.requestFocus();
                            return;
                        }
                        if(edtgiatd.getText().toString().isEmpty()){
                            edtgiatd.setError("Tên không được để trống");
                            edtgiatd.requestFocus();
                            return;
                        }
                        Map<String,Object> map=new HashMap<>();
                        map.put("tentd",edttentd.getText().toString());
                        map.put("giatd",edtgiatd.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("ThucDon")
                                .child(getRef(position).getKey())
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                dialogPlus.show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listtd,parent,false);
        return new ViewHolder(view);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageSuaTD, imageXoaTD;
        TextView txtTenTD,txtGiaTD;
        ConstraintLayout consTD;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            consTD = itemView.findViewById(R.id.consTD);
            imageView = itemView.findViewById(R.id.imageView1);
            imageSuaTD = itemView.findViewById(R.id.imageSuaTD);
            imageXoaTD = itemView.findViewById(R.id.imageXoaTD);
            txtTenTD = itemView.findViewById(R.id.txtTenTD);
            txtGiaTD = itemView.findViewById(R.id.txtGiaTD);

        }

    }

}
