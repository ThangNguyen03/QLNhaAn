package com.example.qlnhaan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.NhanVien;
import Model.ThongKeDoanhThu;

public class AdapterTKDT extends RecyclerView.Adapter<AdapterTKDT.UserViewHolder> {
    public AdapterTKDT(List<ThongKeDoanhThu> dsTKDT) {
        this.dsTKDT = dsTKDT;
    }

    List<ThongKeDoanhThu> dsTKDT;


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listthongkedt,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        ThongKeDoanhThu thongKeDoanhThu=dsTKDT.get(position);
        if(thongKeDoanhThu==null){
            return;
        }
        holder.txtNgayTT.setText(thongKeDoanhThu.getNgaytt());
        holder.txtTrangThaiTT.setText(thongKeDoanhThu.getTrangthaitt());
        holder.txtTenBanTT.setText(thongKeDoanhThu.getTenbantt());
        holder.txtTongTienTT.setText(thongKeDoanhThu.getTongtientt()+"");
    }

    @Override
    public int getItemCount() {
        if(dsTKDT!=null){
            return dsTKDT.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtTrangThaiTT,txtNgayTT,txtTenBanTT,txtTongTienTT;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBanTT=itemView.findViewById(R.id.txtTenBanTT);
            txtNgayTT=itemView.findViewById(R.id.txtNgayTT);
            txtTrangThaiTT=itemView.findViewById(R.id.txtTrangThaiTT);
            txtTongTienTT=itemView.findViewById(R.id.txtTongTienTT);
        }
    }
}
