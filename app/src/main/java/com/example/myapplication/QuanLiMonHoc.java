package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanLiMonHoc extends AppCompatActivity {

    DatabaseReference kdata;
    ListView lvmonhoc, lvid, lvtg;

    ImageView tx;
    ArrayList<String> List;
    ArrayAdapter <String> adapter;
    ArrayList<String> List2;
    ArrayAdapter <String> adapter2;
    ArrayList<Integer> List3;
    ArrayAdapter <Integer> adapter3;
    MonHoc monHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_mon_hoc);
        kdata = FirebaseDatabase.getInstance().getReference("MonHoc");
        lvmonhoc = (ListView)findViewById(R.id.LVmonhoc);
        lvid = (ListView)findViewById(R.id.idkey);
        lvtg = (ListView)findViewById(R.id.thoigianhoc);
        tx = (ImageView)findViewById(R.id.taoorxoa);

        List2 = new ArrayList<>();
        List3 = new ArrayList<>();
        List = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,List2);
        adapter3 = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,List3);


        kdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    List2.add(monHoc.Trangthai);
                    List.add(monHoc.TenMon);
                    List3.add(monHoc.Mamonhoc);
                }
                lvmonhoc.setAdapter(adapter);
                lvid.setAdapter(adapter3);
                lvtg.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent taoxoa = new Intent(QuanLiMonHoc.this,TaoXoaMonHoc.class);
                startActivity(taoxoa);
            }
        });


    }


}