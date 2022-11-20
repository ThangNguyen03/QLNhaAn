package com.example.qlnhaan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import Model.Admin;
import Model.NhanVien;

public class AdapterNV extends BaseAdapter {

TextView txtTen,txtNgaySinh,txtSDT,txtEmail,txtGT;
    List<NhanVien> dsNV;

    public AdapterNV(List<NhanVien> dsNV) {
        this.dsNV = dsNV;
    }

    @Override
    public int getCount() {
        return dsNV.size();
    }

    @Override
    public Object getItem(int i) {
        return dsNV.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view1=inflater.inflate(R.layout.listnv,viewGroup,false);

        txtTen=view1.findViewById(R.id.txtTen);
        txtTen.setText(dsNV.get(i).getTennv());
        txtGT=view1.findViewById(R.id.txtGT);
        txtGT.setText(dsNV.get(i).getGioitinh());
        txtNgaySinh=view1.findViewById(R.id.txtNS);
        txtNgaySinh.setText(dsNV.get(i).getNgaysinh());
        txtEmail=view1.findViewById(R.id.txtEmail);
        txtEmail.setText(dsNV.get(i).getEmail());

        return view1;
    }
}
