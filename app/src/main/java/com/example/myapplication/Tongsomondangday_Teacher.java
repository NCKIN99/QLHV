package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tongsomondangday_Teacher extends AppCompatActivity {

    DatabaseReference kdata;
    ListView Lvmongiangday;
    ArrayList<MonHoc> mangMonHoc;
    MonHoc monHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongsomondangday__teacher);
        Anhxa();
        mangMonHoc = new ArrayList<>();
        Intent nhandl = getIntent();
        String taikhoan = nhandl.getStringExtra("taikhoan");
        Tongsomondangday_Adapter_Teacher adapter_teacher = new Tongsomondangday_Adapter_Teacher(Tongsomondangday_Teacher.this,R.layout.listview_tongsomondangday,mangMonHoc);
        kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    monHoc = ds.getValue(MonHoc.class);
                    if(taikhoan.equals(monHoc.Sdtgiangvien)) {
                        mangMonHoc.add(monHoc);
                    }
                }
                Lvmongiangday.setAdapter(adapter_teacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Lvmongiangday.setOnItemClickListener((parent, view, position, id) -> {
            Intent i2 = new Intent(Tongsomondangday_Teacher.this,Thanhtoanmonhoc_Teacher.class);
            i2.putExtra("mamon",mangMonHoc.get(position)+"");
            i2.putExtra("taikhoan",taikhoan);
            startActivity(i2);
        });
    }
    private void Anhxa(){
        Lvmongiangday = findViewById(R.id.Lvtongsomondangday);
        kdata = FirebaseDatabase.getInstance().getReference();
    }
}