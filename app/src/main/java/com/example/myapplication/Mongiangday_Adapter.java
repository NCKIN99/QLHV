package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.CaptivePortal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Mongiangday_Adapter extends BaseAdapter {

    DatabaseReference kdata;
    Context myContext;
    int myLayout;
    List<MonHoc> arrayMonHoc;
    public Mongiangday_Adapter(Context context, int layout, List<MonHoc> monHocList){
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

        kdata = FirebaseDatabase.getInstance().getReference();

        TextView txtstt = convertView.findViewById(R.id.tvstt);
        txtstt.setText(String.valueOf(position+1));

        TextView txtMonHoc = convertView.findViewById(R.id.tvtenmon);
        txtMonHoc.setText(arrayMonHoc.get(position).TenMon);
        TextView txtMamon = convertView.findViewById(R.id.tvmamon);
        txtMamon.setText(String.valueOf(arrayMonHoc.get(position).Mamonhoc));


        return convertView;
    }
}
