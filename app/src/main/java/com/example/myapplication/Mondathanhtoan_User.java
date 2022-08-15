package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Mondathanhtoan_User extends AppCompatActivity {

    DatabaseReference kdata;
    ListView Lvmondathanhtoan;
    ArrayList<MonHoc> mangMonHoc;
    MonHoc monHoc;
    String tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mondathanhtoan__user);
        Anhxa();
        mangMonHoc = new ArrayList<>();
        Intent nhandl = getIntent();
        String taikhoan = nhandl.getStringExtra("taikhoan");
        Mondathanhtoan_Adapter_User adapter_user = new Mondathanhtoan_Adapter_User(Mondathanhtoan_User.this,R.layout.listview_mondathanhtoan_user,mangMonHoc);
        kdata.child("Thanhtoan_user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Dongtien_User dongtien_user = ds.getValue(Dongtien_User.class);
                    kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds: snapshot.getChildren()) {
                                monHoc = ds.getValue(MonHoc.class);
                                if(String.valueOf(dongtien_user.Mamon).equals(String.valueOf(monHoc.Mamonhoc))&&dongtien_user.Taikhoan.equals(taikhoan)){
                                    mangMonHoc.add(monHoc);
                                }
                            }
                            Lvmondathanhtoan.setAdapter(adapter_user);
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
    private void Anhxa(){
        Lvmondathanhtoan = findViewById(R.id.lvmondathanhtoan_user);
        kdata = FirebaseDatabase.getInstance().getReference();
    }
}