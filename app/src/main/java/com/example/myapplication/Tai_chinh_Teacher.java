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

public class Tai_chinh_Teacher extends AppCompatActivity {

    Button btn_tongsomondd;
    TextView tv_sum_monhoc, tv_sumdongtien;
    DatabaseReference kdata;
    MonHoc monHoc;
    int i =0;
    int i2 = 0;
    int i3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_chinh__teacher);
        Anhxa();
        Intent nhandl = getIntent();
        String tk = nhandl.getStringExtra("taikhoan");
        btn_tongsomondd.setOnClickListener(v -> {
            Intent i = new Intent(Tai_chinh_Teacher.this, Tongsomondangday_Teacher.class);
            i.putExtra("taikhoan",tk);
            startActivity(i);
        });
        kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    if(tk.equals(monHoc.Sdtgiangvien)) {
                        i=i+1;
                    }
                }
                tv_sum_monhoc.setText(i+"");
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
                    if(dongtien_user.Dongtien==dongtien_user.Tienmonhoc){
                        i2=i2+1;
                    }
                    if(snapshot.exists()){
                        i3 = (int)snapshot.getChildrenCount();
                    }

                }
                tv_sumdongtien.setText(i2+"/"+i3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void Anhxa(){
        btn_tongsomondd = findViewById(R.id.btn_tongsomongiangday_teacher);
        tv_sum_monhoc = findViewById(R.id.tv_sum_somonhoc);
        tv_sumdongtien = findViewById(R.id.tv_dathu_tong_teacher);
        kdata = FirebaseDatabase.getInstance().getReference();
    }
}