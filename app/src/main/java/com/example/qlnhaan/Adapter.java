package com.example.qlnhaan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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
        View view1=inflater.inflate(R.layout.listthanhtoan,viewGroup,false);


        return view1;
    }
}
