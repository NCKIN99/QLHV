package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class Thanhtoangiangvien_Admin extends AppCompatActivity {

    DatabaseReference kdata;
    ListView Lvthanhtoangv;
    ArrayList<users> mangMonHoc;
    users user;
    String taikhoandt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoangiangvien__admin);
        Anhxa();
        mangMonHoc = new ArrayList<>();
        Thanhtoangiangvien_Adapter_Admin adapter_admin = new Thanhtoangiangvien_Adapter_Admin(Thanhtoangiangvien_Admin.this,R.layout.listview_thanhtoangiangvien_admin,mangMonHoc);
        kdata.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot  ds: snapshot.getChildren()){
                    users user = ds.getValue(users.class);
                    if(user.kieutk.equals("teacher")){
                        mangMonHoc.add(user);
                    }

                }
                Lvthanhtoangv.setAdapter(adapter_admin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Lvthanhtoangv.setOnItemClickListener((parent, view, position, id) -> {
            taikhoandt = mangMonHoc.get(position).toString();
            Dialoghmh();
        });
    }
    private void Anhxa(){
        Lvthanhtoangv = findViewById(R.id.lvttgv);
        kdata = FirebaseDatabase.getInstance().getReference();
    }
    private void Dialoghmh(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_thanhtoangv);

        TextView tk = dialog.findViewById(R.id.tvtk_ttgv_admin);
        EditText daung = dialog.findViewById(R.id.tv_daung_ttgv_admin);
        EditText thuong = dialog.findViewById(R.id.tv_thuong_ttgv_admin);
        TextView conlai = dialog.findViewById(R.id.tv_conlai_ttgv_admin);
        Button thanhtoan = dialog.findViewById(R.id.btn_ttgv_admin);
        TextView luonggoc = dialog.findViewById(R.id.tv_luonggoc_ttgv_admin);
        Button nhap_thuong = dialog.findViewById(R.id.nhap_thuong);
        Button nhap_daung = dialog.findViewById(R.id.nhap_daung);
        Intent nhandl2 = getIntent();
        String taikhoan2 = nhandl2.getStringExtra("taikhoan");
        kdata.child("Luong_teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Luonggiangvien luonggiangvien = ds.getValue(Luonggiangvien.class);
                    tk.setText(luonggiangvien.Taikhoan+"");
                    luonggoc.setText(luonggiangvien.Luonggoc+"");

                    conlai.setText(Integer.parseInt(luonggoc.getText().toString())+luonggiangvien.Luongthem-luonggiangvien.Dathanhtoan+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nhap_daung.setOnClickListener(v -> {
            kdata.child("Luong_teacher").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Luonggiangvien luonggiangvien = ds.getValue(Luonggiangvien.class);
                        if(taikhoandt.equals(luonggiangvien.Taikhoan)){
                            String keyid = ds.getKey();
                            kdata.child("Luong_teacher").child(keyid).child("Dathanhtoan").setValue(Integer.parseInt(daung.getText().toString()));
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        nhap_thuong.setOnClickListener(v -> {
            kdata.child("Luong_teacher").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Luonggiangvien luonggiangvien = ds.getValue(Luonggiangvien.class);
                        if(taikhoandt.equals(luonggiangvien.Taikhoan)){
                            String keyid = ds.getKey();
                            kdata.child("Luong_teacher").child(keyid).child("Luongthem").setValue(Integer.parseInt(thuong.getText().toString()));
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        thanhtoan.setOnClickListener(v -> {
            kdata.child("Luong_teacher").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds: snapshot.getChildren()){
                        Luonggiangvien luonggiangvien = ds.getValue(Luonggiangvien.class);
                        if(taikhoandt.equals(luonggiangvien.Taikhoan)){
                            String keyid = ds.getKey();
                            kdata.child("Luong_teacher").child(keyid).child("Luonggoc").setValue(0);
                            kdata.child("Luong_teacher").child(keyid).child("Luongthem").setValue(0);
                            kdata.child("Luong_teacher").child(keyid).child("Dathanhtoan").setValue(0);
                            luonggoc.setText(0+"");
                            thuong.setText(0+"");
                            daung.setText(0+"");
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