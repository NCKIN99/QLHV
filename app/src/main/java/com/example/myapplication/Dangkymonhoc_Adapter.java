package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Dangkymonhoc_Adapter extends BaseAdapter {

    Context mycontext;
    int mylayout;
    List<MonHoc> arraymonhoc;
    DatabaseReference kdata;
    Dangkymonhoc_User dangkymonhoc_user;
    public Dangkymonhoc_Adapter (Context context, int Layout, List<MonHoc> monHocList){
        mycontext = context;
        mylayout = Layout;
        arraymonhoc = monHocList;
    }
    @Override
    public int getCount() {
        return arraymonhoc.size();
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
        LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(mylayout,null);

        TextView txtstt = convertView.findViewById(R.id.tvstt_user);
        txtstt.setText(String.valueOf(position+1));

        TextView txtMonHoc = convertView.findViewById(R.id.tvtenmon_user);
        txtMonHoc.setText(arraymonhoc.get(position).TenMon);
        TextView txtMamon = convertView.findViewById(R.id.tvmamon_user);
        txtMamon.setText(String.valueOf(arraymonhoc.get(position).Mamonhoc));
        //Button btndk = convertView.findViewById(R.id.btndk_user);
        kdata = FirebaseDatabase.getInstance().getReference();


        /*btndk.setOnClickListener(v ->{
                kdata.child("MonHocDangHoc").child(txtMamon.getText().toString()).child("1").setValue("2");
        });*/

        return convertView;
    }
}
