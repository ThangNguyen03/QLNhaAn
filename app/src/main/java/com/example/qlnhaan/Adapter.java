package com.example.qlnhaan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import Model.Admin;

public class Adapter extends BaseAdapter {
    private Context context;

    public Adapter(Context context, List<Admin> dsAmin) {
        this.context = context;
        this.dsAmin = dsAmin;
    }

    List<Admin> dsAmin;
    @Override
    public int getCount() {
        return dsAmin.size();
    }

    @Override
    public Object getItem(int i) {
        return dsAmin.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view1=inflater.inflate(R.layout.listadmin,viewGroup,false);

        TextView txtemail=view1.findViewById(R.id.txtemail);
        txtemail.setText(dsAmin.get(i).getEmail());

        TextView txtpass=view1.findViewById(R.id.txtpass);
        txtpass.setText(dsAmin.get(i).getPassword());
        return view1;
    }
}
