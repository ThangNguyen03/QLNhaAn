package com.example.qlnhaan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Model.NhanVien;
import Model.ThucDon;

public class AdapterThucDon extends RecyclerView.Adapter<AdapterThucDon.ViewHolder> {
    Context context;
    List<ThucDon> dsThucDon;

    public AdapterThucDon(Context context, List<ThucDon> dsThucDon) {
        this.context = context;
        this.dsThucDon = dsThucDon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listtd,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThucDon thucDon=dsThucDon.get(position);
        if(thucDon==null){
            return;
        }

        holder.txtTenTD.setText(thucDon.getTentd());

        String imageUri;
        imageUri=thucDon.getAnhtd();
       Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dsThucDon.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
ImageView imageView;
TextView txtTenTD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView1);
            txtTenTD=itemView.findViewById(R.id.txtTenTD);
        }
    }
}
