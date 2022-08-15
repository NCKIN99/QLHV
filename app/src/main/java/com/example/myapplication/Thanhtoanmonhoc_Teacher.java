package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Thanhtoanmonhoc_Teacher extends AppCompatActivity {

    DatabaseReference kdata;
    ListView Lvthanhtoanmh;
    ArrayList<users> mangMonHoc;
    users user;
    String tk, mamonhoc, taikhoandt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoanmonhoc__teacher);
        Anhxa();
        mangMonHoc = new ArrayList<>();
        Intent nhandl = getIntent();
        String taikhoan = nhandl.getStringExtra("taikhoan");
        String mamon = nhandl.getStringExtra("mamon");
        Thanhtoanmonhoc_Adapter_Teacher adapter = new Thanhtoanmonhoc_Adapter_Teacher(Thanhtoanmonhoc_Teacher.this, R.layout.listview_thanhtoanmonhoc_teacher,mangMonHoc);
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
                                kdata.child("users").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                        for(DataSnapshot ds3: snapshot3.getChildren()){
                                            user = ds3.getValue(users.class);
                                            if(tk2.equals(String.valueOf(user.taikhoan))&&mh.equals(mamon)){
                                                mangMonHoc.add(user);
                                            }

                                        }
                                        Lvthanhtoanmh.setAdapter(adapter);
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
        Lvthanhtoanmh.setOnItemClickListener((parent, view, position, id) -> {
            mamonhoc=mangMonHoc.get(position).toString();
            taikhoandt = mangMonHoc.get(position).toString();
            Dialoghmh();
        });
    }
    private void Anhxa(){
        Lvthanhtoanmh = findViewById(R.id.lvthanhtoanmh_teacher);
        kdata = FirebaseDatabase.getInstance().getReference();
    }
    private void Dialoghmh(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_thanhtoanmh_teacher);

        TextView tk = dialog.findViewById(R.id.tvtk_ttmh_teacher);
        TextView dadong = dialog.findViewById(R.id.tvdadong_teacher);
        TextView tong = dialog.findViewById(R.id.tvtongdadong_teacher);
        EditText donghientai = dialog.findViewById(R.id.edtdongtien_teacher);
        Button dongtien = dialog.findViewById(R.id.btndongtien_teacher);
        Intent nhandl2 = getIntent();
        String taikhoan2 = nhandl2.getStringExtra("taikhoan");
        kdata.child("Thanhtoan_user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Dongtien_User dongtien_user = ds.getValue(Dongtien_User.class);
                    if(taikhoandt.equals(dongtien_user.Taikhoan)){
                        String keyid = ds.getKey();
                        tk.setText(dongtien_user.Taikhoan+"");
                        dadong.setText(dongtien_user.Dongtien+"");
                        tong.setText(dongtien_user.Tienmonhoc+"");

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        dongtien.setOnClickListener(v -> {
            if(donghientai.getText().toString().length()>0) {
                if (Integer.parseInt(donghientai.getText().toString())>Integer.parseInt(tong.getText().toString()))
                    Toast.makeText(Thanhtoanmonhoc_Teacher.this,"Nhập lại số tiền",Toast.LENGTH_SHORT).show();
                else if(Integer.parseInt(donghientai.getText().toString())<=Integer.parseInt(tong.getText().toString())){
                    kdata.child("Thanhtoan_user").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                Dongtien_User dongtien_user = ds.getValue(Dongtien_User.class);
                                if (taikhoandt.equals(dongtien_user.Taikhoan)) {
                                    String keyid = ds.getKey();
                                    kdata.child("Thanhtoan_user").child(keyid).child("Dongtien").setValue(Integer.parseInt(donghientai.getText().toString()));

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }
            else
                Toast.makeText(Thanhtoanmonhoc_Teacher.this,"Nhập số tiền",Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }
}