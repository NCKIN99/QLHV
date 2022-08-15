package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Monhocdanghoc_Adapter_user extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<MonHoc> arrayMonHoc;
    public Monhocdanghoc_Adapter_user(Context context, int layout, List<MonHoc> monHocList){
        myContext = context;
        myLayout = layout;
        arrayMonHoc = monHocList;
    }
    @Override
    public int getCount() {
        return arrayMonHoc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);


        TextView txtstt = convertView.findViewById(R.id.tvstt_mhdhhv);
        txtstt.setText(String.valueOf(position+1));

        TextView txtMonHoc = convertView.findViewById(R.id.tvtenmon_mhdhhv);
        txtMonHoc.setText(arrayMonHoc.get(position).TenMon);
        TextView txtMamon = convertView.findViewById(R.id.tvmamon_mhdhhv);
        txtMamon.setText(String.valueOf(arrayMonHoc.get(position).Mamonhoc));
        return convertView;
    }
}
