package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Tongsomondangday_Adapter_Teacher extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<MonHoc> mymonHocList;
    public Tongsomondangday_Adapter_Teacher(Context context, int layout, List<MonHoc> monHocList){
        myContext = context;
        myLayout = layout;
        mymonHocList = monHocList;
    }
    @Override
    public int getCount() {
        return mymonHocList.size();
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


        TextView txtstt = convertView.findViewById(R.id.tvstt_tsmdd);
        txtstt.setText(String.valueOf(position+1));

        TextView txtMonHoc = convertView.findViewById(R.id.tvtenmon_tsmdd);
        txtMonHoc.setText(mymonHocList.get(position).TenMon);
        TextView txtMamon = convertView.findViewById(R.id.tvmamon_tsmdd);
        txtMamon.setText(String.valueOf(mymonHocList.get(position).Mamonhoc));
        return convertView;
    }
}
