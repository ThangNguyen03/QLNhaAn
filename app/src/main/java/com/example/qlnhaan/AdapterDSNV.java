package com.example.qlnhaan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.NhanVien;

public class AdapterDSNV extends RecyclerView.Adapter<AdapterDSNV.UserViewHolder> {
List<NhanVien> dsNV;

    public AdapterDSNV(List<NhanVien> dsNV) {
        this.dsNV = dsNV;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listnv,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        NhanVien nhanVien=dsNV.get(position);
        if(nhanVien==null){
            return;
        }
       holder.txtTen.setText("Ten: "+ nhanVien.getTennv());
        holder.txtGT.setText(nhanVien.getGioitinh());
        holder.txtNgaySinh.setText(nhanVien.getNgaysinh());
        holder.txtEmail.setText(nhanVien.getEmail());
    }

    @Override
    public int getItemCount() {
        if(dsNV!=null){
            return dsNV.size();
        }
        return 0;
    }

    public  class UserViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen,txtNgaySinh,txtEmail,txtGT;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen=itemView.findViewById(R.id.txtTen);
            txtGT=itemView.findViewById(R.id.txtGT);
            txtNgaySinh=itemView.findViewById(R.id.txtNS);
            txtEmail=itemView.findViewById(R.id.txtEmail);
    }


    }
}
