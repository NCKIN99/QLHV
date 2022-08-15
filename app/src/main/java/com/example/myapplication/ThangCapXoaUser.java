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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThangCapXoaUser extends AppCompatActivity {

    DatabaseReference kdata, kdata2;
    Button xoauser, setlevel;
    EditText iduser, idlevel;
    RadioButton rdbuser, rdbteacher;
    String keyid, keyid2;
    String quyentk="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thang_cap_xoa_user);
        kdata = FirebaseDatabase.getInstance().getReference("users");
        xoauser = (Button)findViewById(R.id.btnxoauser);
        setlevel = (Button)findViewById(R.id.btnsetlevel);
        idlevel = (EditText)findViewById(R.id.idlevel);
        iduser = (EditText)findViewById(R.id.idxoauseer);
        rdbteacher = (RadioButton)findViewById(R.id.RdbTeacher);
        rdbuser = (RadioButton)findViewById(R.id.Rdbuser);
        kdata2 = FirebaseDatabase.getInstance().getReference();

        xoauser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdata.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (iduser.getText().toString().length()>0) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                users user = ds.getValue(users.class);
                                if (user.taikhoan.equals(iduser.getText().toString())) {
                                    keyid = ds.getKey();
                                    kdata.child(keyid).removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            Toast.makeText(ThangCapXoaUser.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    Toast.makeText(ThangCapXoaUser.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else
                            Toast.makeText(ThangCapXoaUser.this, "Nhập tài khoản cần xóa", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        setlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdbuser.isChecked())
                    quyentk = "user";
                if(rdbteacher.isChecked())
                    quyentk = "teacher";
                kdata.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(idlevel.getText().toString().length()>0) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                users user = ds.getValue(users.class);
                                if (user.taikhoan.equals(idlevel.getText().toString())) {
                                    keyid2 = ds.getKey();
                                    if (quyentk.length() > 2) {
                                        Toast.makeText(ThangCapXoaUser.this, "cài đặt quyền tài khoản thành công", Toast.LENGTH_SHORT).show();
                                        kdata.child(keyid2).child("kieutk").setValue(quyentk);
                                        if(quyentk.equals("teacher")){
                                            Luonggiangvien luonggiangvien = new Luonggiangvien(user.taikhoan,1000000,0,0);
                                            kdata2.child("Luong_teacher").push().setValue(luonggiangvien);
                                        }
                                        quyentk = "2";
                                    }
                                }
                            }
                        }
                        else
                            Toast.makeText(ThangCapXoaUser.this, "Nhập tài khoản cần chuyển quyền", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}