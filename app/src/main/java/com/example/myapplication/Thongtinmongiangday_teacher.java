package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Thongtinmongiangday_teacher extends AppCompatActivity {

    TextView mamonhoc, tenmon, giangvienday, sdtgiangvien, syso, sobuoi, ngayhoc;
    DatabaseReference kdata;
    MonHoc monHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinmongiangday_teacher);
        mamonhoc = findViewById(R.id.tvthongtin_mamon);
        tenmon = findViewById(R.id.tvthongtin_tenmon);
        giangvienday = findViewById(R.id.tvthongtin_tengv);
        sdtgiangvien = findViewById(R.id.tvthongtin_sdtgv);
        syso = findViewById(R.id.tvthongtin_syso);
        sobuoi = findViewById(R.id.tvthongtin_sobuoi);
        ngayhoc = findViewById(R.id.tvthongtin_ngaybatdau);
        kdata = FirebaseDatabase.getInstance().getReference("MonHoc");

        Intent ndl = getIntent();
        String mamonhoc1 = ndl.getStringExtra("mamon");

        kdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    monHoc =ds.getValue(MonHoc.class);
                    if(String.valueOf(monHoc.Mamonhoc).equals(mamonhoc1)){
                        mamonhoc.setText(String.valueOf(monHoc.Mamonhoc));
                        tenmon.setText(monHoc.TenMon);
                        giangvienday.setText(monHoc.Giangvienday);
                        sdtgiangvien.setText(monHoc.Sdtgiangvien);
                        syso.setText(monHoc.Syso);
                        sobuoi.setText(monHoc.SoTiet);
                        ngayhoc.setText(monHoc.Ngaybatdau+"/"+monHoc.Thangbatdau+"/"+monHoc.Nambatdau);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}