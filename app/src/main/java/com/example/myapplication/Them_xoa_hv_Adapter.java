package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Them_xoa_hv_Adapter extends BaseAdapter {

    Context mycontext;
    int mylayout;
    List<users> arrayuser;
    DatabaseReference kdata;
    public Them_xoa_hv_Adapter(Context context, int layout, List<users> usersList){
        mycontext = context;
        mylayout = layout;
        arrayuser = usersList;
    }


    @Override
    public int getCount() {
        return arrayuser.size();
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
        LayoutInflater inflater =   (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(mylayout, null);
        kdata = FirebaseDatabase.getInstance().getReference();

        TextView stt = convertView.findViewById(R.id.stt_txhv);
        TextView sdt = convertView.findViewById(R.id.sdt_txhv);
        TextView ten = convertView.findViewById(R.id.tenhv_txhv);
        stt.setText(""+(position+1));
        sdt.setText(arrayuser.get(position).taikhoan);
        ten.setText(arrayuser.get(position).hoten);

        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
