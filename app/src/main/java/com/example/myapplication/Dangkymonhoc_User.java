package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dangkymonhoc_User extends AppCompatActivity {

    ArrayList<MonHoc> arrayList;
    DatabaseReference kdata, fdata;
    ListView listView;
    MonHoc monHoc;
    String mamonhoc;
    String taikhoan;
    int dem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangkymonhoc__user);
        listView = findViewById(R.id.lvdkmonhoc);
        arrayList = new ArrayList<>();
        kdata = FirebaseDatabase.getInstance().getReference();
        fdata = FirebaseDatabase.getInstance().getReference();
        Intent nhandl = getIntent();
        String tk = nhandl.getStringExtra("taikhoan");
        taikhoan = tk;
        Dangkymonhoc_Adapter adapter = new Dangkymonhoc_Adapter(Dangkymonhoc_User.this,R.layout.listview_dkmonhoc_user,arrayList);


        kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    arrayList.add(monHoc);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mamonhoc=arrayList.get(position).toString();
                Dialogdkmh();
            }
        });



    }
    private void Dialogdkmh(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_dkmh_user);

        Intent nhandl = getIntent();
        String tk = nhandl.getStringExtra("taikhoan");
        TextView tenmon = dialog.findViewById(R.id.tvtenmondkmh_user);
        TextView mamon = dialog.findViewById(R.id.tvmamondkmh_user);
        TextView tengv = dialog.findViewById(R.id.tvtengv_user);
        TextView syso1 = dialog.findViewById(R.id.tvss_user);
        TextView syso2 = dialog.findViewById(R.id.tvss2_user);
        TextView hocphi = dialog.findViewById(R.id.tvhocphi);
        Button dangky = dialog.findViewById(R.id.btndkmh_user);
        kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    if(monHoc.Mamonhoc==Integer.parseInt(mamonhoc)){
                        tenmon.setText(monHoc.TenMon);
                        mamon.setText(monHoc.Mamonhoc+"");
                        syso2.setText(monHoc.Syso+"");
                        tengv.setText(monHoc.Giangvienday);
                        hocphi.setText(monHoc.Giamonhoc);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        kdata.child("MonHocDangHoc").child(mamonhoc).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    dem = (int)snapshot.getChildrenCount();
                    syso1.setText(dem+"");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        dangky.setOnClickListener(v -> {
            if(dem>=Integer.parseInt(syso2.getText().toString())){
                Toast.makeText(this,"Môn học đầy",Toast.LENGTH_SHORT).show();
            }
            else{
                Dongtien_User dongtien_user = new Dongtien_User(taikhoan,Integer.parseInt(hocphi.getText().toString()),0,Integer.parseInt(mamonhoc));
                kdata.child("Thanhtoan_user").push().setValue(dongtien_user);
                fdata.child("MonHocDangHoc").child(mamon.getText().toString()).child(tk).setValue(tk);
            }
        });
        dialog.show();
    }
}