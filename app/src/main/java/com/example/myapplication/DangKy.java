package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DangKy extends AppCompatActivity {

    EditText tk_dk, mk_dk, email, hoten;
    RadioButton rdbnam, rdbnu;
    Button btndk, btndn, kttk;
    String gioitinh = null;
    DatabaseReference kdata, mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        mdata = FirebaseDatabase.getInstance().getReference("MonHocDangHoc");
        kdata = FirebaseDatabase.getInstance().getReference("users");
        tk_dk = (EditText)findViewById(R.id.nhaptk);
        mk_dk = (EditText)findViewById(R.id.nhapmk);
        hoten = (EditText) findViewById(R.id.nhaphoten);
        email = (EditText)findViewById(R.id.nhapemail);
        rdbnam = (RadioButton)findViewById(R.id.nam);
        rdbnu = (RadioButton)findViewById(R.id.nu);
        btndk = (Button)findViewById(R.id.btndanky);
        btndn = (Button)findViewById(R.id.btndannhap);

        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dn = new Intent(DangKy.this,MainActivity.class);
                startActivity(dn);
            }
        });


        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk_dangky = tk_dk.getText().toString();
                String mk_dangky = mk_dk.getText().toString();
                String email_dangky = email.getText().toString();
                String hoten_dangky = hoten.getText().toString();
                if(rdbnam.isChecked()){
                    gioitinh = "nam";
                }
                if(rdbnu.isChecked()){
                    gioitinh = "nữ";
                }
                if(tk_dangky.length()>0 && mk_dangky.length()>0 && email_dangky.length()>0 && hoten_dangky.length()>0) {
                    users users = new users(tk_dk.getText().toString(), mk_dk.getText().toString(), hoten.getText().toString(), email.getText().toString(), gioitinh, "user","no");
                    kdata.push().setValue(users);
                    kdata.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                    Toast.makeText(DangKy.this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();

            }
        });


    }

}