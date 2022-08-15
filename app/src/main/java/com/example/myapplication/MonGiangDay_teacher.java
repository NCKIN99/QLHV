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

public class MonGiangDay_teacher extends AppCompatActivity {
    DatabaseReference kdata;
    ListView Lvmongiangday;
    ArrayList<MonHoc> mangMonHoc;
    MonHoc monHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_giang_day_teacher);
        kdata = FirebaseDatabase.getInstance().getReference();
        Lvmongiangday = findViewById(R.id.lvmongiangdaysinhvien);
        mangMonHoc = new ArrayList<>();

        Intent nhandl = getIntent();
        String taikhoan = nhandl.getStringExtra("taikhoan");
        Mongiangday_Adapter adapter = new Mongiangday_Adapter(MonGiangDay_teacher.this,R.layout.listview_mongiangday,mangMonHoc);
        kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    if(taikhoan.equals(monHoc.Sdtgiangvien)) {
                        mangMonHoc.add(monHoc);
                    }
                }
                Lvmongiangday.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Lvmongiangday.setOnItemClickListener((parent, view, position, id) -> {
            Intent i2 = new Intent(MonGiangDay_teacher.this,Thongtinmongiangday_teacher.class);
            i2.putExtra("mamon",mangMonHoc.get(position)+"");
            startActivity(i2);
        });


    }
}