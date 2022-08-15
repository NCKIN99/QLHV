package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView btndk1;
    EditText tk_sdt, mk;
    Button  btndn1;
    DatabaseReference kdata;
    String TK_login, Mk_login, kieutk, Keyid;
    Intent truyendl, truyendl2, truyendl3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tk_sdt = (EditText)findViewById(R.id.editTextsdt);
        mk = findViewById(R.id.editTextNumberPassword);
        btndk1 = (TextView)findViewById(R.id.buttondk);
        btndn1 = (Button)findViewById(R.id.buttondn);
        kdata = FirebaseDatabase.getInstance().getReference();

        btndk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dk = new Intent(MainActivity.this, DangKy.class);
                startActivity(dk);
            }
        });
        btndn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kdata.child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren()) {
                            users user = ds.getValue(users.class);
                            if(tk_sdt.getText().toString().equals(user.taikhoan)&&mk.getText().toString().equals(user.matkhau)) {
                                Keyid = ds.getKey();
                                TK_login = user.taikhoan;
                                Mk_login = user.matkhau;
                                kieutk = user.kieutk;
                                truyendl = new Intent(MainActivity.this, ManHinhChinhUser.class);
                                truyendl.putExtra("hoten",user.hoten);
                                truyendl.putExtra("taikhoan",user.taikhoan);
                                truyendl.putExtra("email",user.email);
                                truyendl.putExtra("gioitinh",user.gioitinh);
                                truyendl.putExtra("kieutk",user.kieutk);
                                truyendl.putExtra("keyid",Keyid);
                                truyendl2 = new Intent(MainActivity.this, ManHinhChinh_Admin.class);
                                truyendl2.putExtra("hoten",user.hoten);
                                truyendl2.putExtra("taikhoan",user.taikhoan);
                                truyendl2.putExtra("email",user.email);
                                truyendl2.putExtra("gioitinh",user.gioitinh);
                                truyendl2.putExtra("keyid",Keyid);
                                truyendl2.putExtra("kieutk",user.kieutk);
                                truyendl3 = new Intent(MainActivity.this, ManHinhChinhTeacher.class);
                                truyendl3.putExtra("hoten",user.hoten);
                                truyendl3.putExtra("taikhoan",user.taikhoan);
                                truyendl3.putExtra("email",user.email);
                                truyendl3.putExtra("gioitinh",user.gioitinh);
                                truyendl3.putExtra("keyid",Keyid);
                                truyendl3.putExtra("kieutk",user.kieutk);
                            }
                        }
                        if(tk_sdt.getText().toString().equals(TK_login) & mk.getText().toString().equals(Mk_login)){
                            if(kieutk.equals("admin")){
                                startActivity(truyendl2);
                            }
                            if(kieutk.equals("user")){
                                startActivity(truyendl);
                            }
                            if(kieutk.equals("teacher")){
                                startActivity(truyendl3);
                            }

                        }
                        else
                            Toast.makeText(MainActivity.this,"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                /*kdata.child("users").child("taikhoan").equals(tk_sdt.getText().toString());
                kdata.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren()){
                            users user =ds.getValue(users.class);
                            if(user.matkhau.equals(mk.getText().toString())){
                                Keyid = ds.getKey();
                                TK_login = user.taikhoan;
                                Mk_login = user.matkhau;
                                kieutk = user.kieutk;
                                truyendl = new Intent(MainActivity.this, ManHinhChinhUser.class);
                                truyendl.putExtra("hoten",user.hoten);
                                truyendl.putExtra("taikhoan",user.taikhoan);
                                truyendl.putExtra("email",user.email);
                                truyendl.putExtra("gioitinh",user.gioitinh);
                                truyendl.putExtra("kieutk",user.kieutk);
                                truyendl.putExtra("keyid",Keyid);
                                truyendl2 = new Intent(MainActivity.this, ManHinhChinh_Admin.class);
                                truyendl2.putExtra("hoten",user.hoten);
                                truyendl2.putExtra("taikhoan",user.taikhoan);
                                truyendl2.putExtra("email",user.email);
                                truyendl2.putExtra("gioitinh",user.gioitinh);
                                truyendl2.putExtra("keyid",Keyid);
                                truyendl2.putExtra("kieutk",user.kieutk);
                                truyendl3 = new Intent(MainActivity.this, ManHinhChinhTeacher.class);
                                truyendl3.putExtra("hoten",user.hoten);
                                truyendl3.putExtra("taikhoan",user.taikhoan);
                                truyendl3.putExtra("email",user.email);
                                truyendl3.putExtra("gioitinh",user.gioitinh);
                                truyendl3.putExtra("keyid",Keyid);
                                truyendl3.putExtra("kieutk",user.kieutk);
                            }

                        }
                        if(tk_sdt.getText().toString().equals(TK_login) & mk.getText().toString().equals(Mk_login)){
                            if(kieutk.equals("admin")){
                                startActivity(truyendl2);
                            }
                            if(kieutk.equals("user")){
                                startActivity(truyendl);
                            }
                            if(kieutk.equals("teacher")){
                                startActivity(truyendl3);
                            }

                        }
                        else
                            Toast.makeText(MainActivity.this,"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });
    }
}