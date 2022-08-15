package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tai_chinh_Admin extends AppCompatActivity {

    TextView Sum_mh, Dathu_tong1, Dathu_tong2, Sum_gv, Datra_chuatra, Condu;
    Button Xem_dathu_tong, Xem_datra_chuatra,btn_thanhtoan_gv;
    DatabaseReference kdata;
    int dem1=0;
    int sum_tienmh=0;
    int sum_dathu=0;
    MonHoc monHoc;
    int i4=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_chinh__admin);
        Anhxa();
        kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    sum_tienmh=sum_tienmh+Integer.parseInt(monHoc.Giamonhoc);

                }
                if(snapshot.exists()){
                    dem1 = (int)snapshot.getChildrenCount();
                    Sum_mh.setText(dem1+"");

                }
                Dathu_tong2.setText(sum_tienmh+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        kdata.child("Thanhtoan_user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Dongtien_User dongtien_user = ds.getValue(Dongtien_User.class);
                    sum_dathu=sum_dathu+dongtien_user.Dongtien;
                }
                Dathu_tong1.setText(sum_dathu+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        kdata.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    users user = ds.getValue(users.class);
                    if(user.kieutk.equals("teacher")){
                        i4=i4+1;
                    }
                }
                Sum_gv.setText(i4+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_thanhtoan_gv.setOnClickListener(v -> {
            Intent i = new Intent(Tai_chinh_Admin.this,Thanhtoangiangvien_Admin.class);
            startActivity(i);
        });


    }

    private void Anhxa(){
        btn_thanhtoan_gv = findViewById(R.id.btn_sum_giangvien_admin);
        Sum_mh = findViewById(R.id.sum_mongiangday_admin);
        Sum_gv = findViewById(R.id.sum_giangvien_admin);
        Dathu_tong1 = findViewById(R.id.dathu_tong1_admin);
        Dathu_tong2 = findViewById(R.id.dathu_tong2_admin);
        Datra_chuatra = findViewById(R.id.datra_chuatra_admin);
        Condu = findViewById(R.id.condu_admin);
        Xem_dathu_tong = findViewById(R.id.btn_dathu_chuathu_admin);
        Xem_datra_chuatra = findViewById(R.id.btn_datra_chuatra_admin);
        kdata = FirebaseDatabase.getInstance().getReference();
    }
}