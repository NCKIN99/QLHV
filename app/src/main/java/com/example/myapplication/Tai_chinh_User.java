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

public class Tai_chinh_User extends AppCompatActivity {

    Button btn_datt;
    DatabaseReference kdata;
    TextView tv, tv2, tv3;
    int i2 =0;
    int i3 =0;
    int congno=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_chinh__user);
        Anhxa();
        Intent nhandl = getIntent();
        String tk = nhandl.getStringExtra("taikhoan");
        kdata.child("Thanhtoan_user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Dongtien_User dongtien_user = ds.getValue(Dongtien_User.class);
                    if(dongtien_user.Taikhoan.equals(tk)) {
                        i3 = i3+1;
                        if (dongtien_user.Dongtien == dongtien_user.Tienmonhoc) {
                            i2 = i2 + 1;
                        }
                        if(dongtien_user.Dongtien == 0){
                            congno = congno+dongtien_user.Tienmonhoc;
                        }
                    }

                }
                tv3.setText(congno+"");
                tv2.setText(i2+"");
                tv.setText(i2+"/"+i3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_datt.setOnClickListener(v -> {
            Intent i = new Intent(Tai_chinh_User.this,Mondathanhtoan_User.class);
            i.putExtra("taikhoan",tk);
            startActivity(i);
        });


    }
    private void Anhxa(){
        tv3 = findViewById(R.id.tv_congno_user);
        tv2 = findViewById(R.id.tv_mondathanhtoan_user);
        tv = findViewById(R.id.tv_dathanhtoan_user);
        btn_datt = findViewById(R.id.btn_xem_mondathanhtoan_user);
        kdata = FirebaseDatabase.getInstance().getReference();
    }
}