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

public class Monhocdanghoc_user extends AppCompatActivity {

    DatabaseReference kdata, mdata;
    ListView Lvmondanghoc;
    ArrayList<MonHoc> mangMonHoc;
    MonHoc monHoc;
    String tk, mamonhoc;
    int dem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monhocdanghoc_user);
        Lvmondanghoc = findViewById(R.id.lvmonhocdanghocsv);
        kdata = FirebaseDatabase.getInstance().getReference();
        mdata = FirebaseDatabase.getInstance().getReference();
        mangMonHoc = new ArrayList<>();
        Intent nhandl = getIntent();
        String taikhoan = nhandl.getStringExtra("taikhoan");
        Monhocdanghoc_Adapter_user adapter = new Monhocdanghoc_Adapter_user(Monhocdanghoc_user.this,R.layout.listview_monhocdanghoc_user,mangMonHoc);
        kdata.child("MonHocDangHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String mh = ds.getKey();
                    kdata.child("MonHocDangHoc").child(mh).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            for(DataSnapshot ds2: snapshot2.getChildren()){
                                tk = ds2.getValue().toString();
                                String tk2 = tk;
                                kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                        for(DataSnapshot ds3: snapshot3.getChildren()){
                                            monHoc = ds3.getValue(MonHoc.class);
                                            if(tk2.equals(String.valueOf(taikhoan))&&mh.equals(String.valueOf(monHoc.Mamonhoc))){
                                                mangMonHoc.add(monHoc);
                                            }

                                        }
                                        Lvmondanghoc.setAdapter(adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Lvmondanghoc.setOnItemClickListener((parent, view, position, id) -> {
            mamonhoc=mangMonHoc.get(position).toString();
            Dialoghmh();
        });

    }
    private void Dialoghmh(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_huymonhoc_user);

        TextView tenmon = dialog.findViewById(R.id.tvtenmonhmh_user);
        TextView mamon = dialog.findViewById(R.id.tvmamonhmh_user);
        TextView tengv = dialog.findViewById(R.id.tvtengv_hmhuser);
        TextView syso1 = dialog.findViewById(R.id.tvss1_user);
        TextView syso2 = dialog.findViewById(R.id.tvss3_user);
        Button huy = dialog.findViewById(R.id.btnhmh_user);
        Intent nhandl2 = getIntent();
        String taikhoan2 = nhandl2.getStringExtra("taikhoan");
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
        huy.setOnClickListener(v -> {
            kdata.child("MonHocDangHoc").child(mamonhoc+"").child(taikhoan2).removeValue((error, ref) -> Toast.makeText(Monhocdanghoc_user.this, "Hủy thành công", Toast.LENGTH_SHORT).show());
            mdata.child("Thanhtoan_user").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Dongtien_User dongtien_user = ds.getValue(Dongtien_User.class);
                        if(taikhoan2.equals(dongtien_user.Taikhoan)&&String.valueOf(mamonhoc).equals(String.valueOf(dongtien_user.Mamon))){
                            String keyid = ds.getKey();
                            mdata.child("Thanhtoan_user").child(keyid).removeValue((error, ref) -> Toast.makeText(Monhocdanghoc_user.this, "Hủy thành công", Toast.LENGTH_SHORT).show());
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        dialog.show();
    }
}