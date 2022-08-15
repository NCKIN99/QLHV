package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanLyNguoiDung extends AppCompatActivity {

    DatabaseReference kdata;
    ListView lvsdt, lvid, lvkieutk;

    ImageView tx;
    ArrayList<String> List;
    ArrayAdapter<String> adapter;
    ArrayList<String> List2;
    ArrayAdapter <String> adapter2;
    ArrayList<String> List3;
    ArrayAdapter <String> adapter3;
    users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nguoi_dung);
        kdata = FirebaseDatabase.getInstance().getReference("users");
        lvsdt = (ListView)findViewById(R.id.sdt_user);
        lvid = (ListView)findViewById(R.id.idkeyuser);
        lvkieutk = (ListView)findViewById(R.id.kieutk);
        tx = (ImageView)findViewById(R.id.taoorxoa);
        Intent i = getIntent();
        String tk = i.getStringExtra("taikhoan");

        List2 = new ArrayList<>();
        List3 = new ArrayList<>();
        List = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List2);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List3);


        kdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String key = ds.getKey();
                    users = ds.getValue(users.class);
                    List.add(users.hoten);
                    List2.add(users.taikhoan);
                    List3.add(users.kieutk);
                    //Toast.makeText(QuanLyNguoiDung.this, users.toString(), Toast.LENGTH_SHORT).show();
                }
                lvid.setAdapter(adapter);
                lvsdt.setAdapter(adapter2);
                lvkieutk.setAdapter(adapter3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thangcapxoa = new Intent(QuanLyNguoiDung.this,ThangCapXoaUser.class);
                thangcapxoa.putExtra("taikhoan",tk);
                startActivity(thangcapxoa);
            }
        });


    }

}