package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class quanlysinhvien_teacher extends AppCompatActivity {

    ListView listView;
    ArrayList<MonHoc> monHocArrayList;
    DatabaseReference kdata;
    MonHoc monHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlysinhvien_teacher);
        listView = findViewById(R.id.LVmongiangday);
        kdata = FirebaseDatabase.getInstance().getReference();
        monHocArrayList = new ArrayList<>();
        Intent nhandl2 = getIntent();
        String taikhoan = nhandl2.getStringExtra("taikhoan");
        quanlysinhvien_Adapter adapter = new quanlysinhvien_Adapter(quanlysinhvien_teacher.this,R.layout.listview_quanlysinhvien_teacher,monHocArrayList);
        kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    if(taikhoan.equals(monHoc.Sdtgiangvien)) {
                        monHocArrayList.add(monHoc);
                    }
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i2 = new Intent(quanlysinhvien_teacher.this,Them_xoa_hv_teacher.class);
                i2.putExtra("mamon",monHocArrayList.get(position)+"");
                startActivity(i2);
            }
        });
    }
}