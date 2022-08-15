package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Them_xoa_hv_teacher extends AppCompatActivity {

    EditText tkhv;
    Button themhv;
    ListView listView;
    DatabaseReference kdata;
    ArrayList<users> arrayList;
    users user;
    String tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_xoa_hv_teacher);
        listView = findViewById(R.id.lvthemxoahv);
        tkhv = findViewById(R.id.edtthemhv_teaccher);
        themhv = findViewById(R.id.btnthemhv_teacher);
        kdata = FirebaseDatabase.getInstance().getReference();
        arrayList = new ArrayList<>();
        Intent nhandl = getIntent();
        String aa = nhandl.getStringExtra("mamon");
        Them_xoa_hv_Adapter adapter = new Them_xoa_hv_Adapter(Them_xoa_hv_teacher.this,R.layout.listview_themxoahv_teacher,arrayList);

        kdata.child("MonHocDangHoc").child(aa).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    tk = ds.getValue().toString();
                    String tk2 = tk;
                    kdata.child("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds: snapshot.getChildren()){
                                user = ds.getValue(users.class);
                                    if (tk2.equals(user.taikhoan)) {
                                        arrayList.add(user);
                                }
                            }
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        themhv.setOnClickListener(v -> {
            if(tkhv.getText().toString().length()==10){
                kdata.child("MonHocDangHoc").child(aa).child(tkhv.getText().toString()).setValue(tkhv.getText().toString());
            }
            else
                Toast.makeText(Them_xoa_hv_teacher.this,"Nhập tài khoản cần thêm",Toast.LENGTH_SHORT).show();
        });

    }
}