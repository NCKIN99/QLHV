package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Thoikhoabieu_user extends AppCompatActivity {
    ArrayList<MonHoc> mangMonhoc;
    ListView lvlichgd;
    TextView ngaybd, ngaykt;
    Button btnsang, btnchieu, btntoi, btntuansau, btntuantruoc;
    DatabaseReference kdata;
    DatabaseReference kdata2;
    public static String thoigianday = "sang";
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    public static String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoikhoabieu_user);
        Intent nhandl = getIntent();
        String tk = nhandl.getStringExtra("taikhoan");
        Anhxa();
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd/MM/yyyy");
        calendar1.add(Calendar.DATE,-calendar1.get(Calendar.DAY_OF_WEEK)+2);
        calendar2.add(Calendar.DATE,-calendar2.get(Calendar.DAY_OF_WEEK)+8);
        ngaybd.append(dinhdangngay.format(calendar1.getTime()));
        ngaykt.append(dinhdangngay.format(calendar2.getTime()));
            Thoikhoabieu_Adapter_user adapter = new Thoikhoabieu_Adapter_user(Thoikhoabieu_user.this,R.layout.listview_thoikhoabieu_user,mangMonhoc);
        if(thoigianday.equals("sang")){
            thoigianday="sang";
            btnsang.setBackgroundColor(Color.parseColor("#4CAF50"));
            btntoi.setBackgroundColor(Color.parseColor("#3F51B5"));
            btnchieu.setBackgroundColor(Color.parseColor("#3F51B5"));
            mangMonhoc.removeAll(mangMonhoc);
            kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        MonHoc monHoc = ds.getValue(MonHoc.class);

                        //if(tk.equals(monHoc.Sdtgiangvien)) {
                            if (monHoc.Thu2 == 1 | monHoc.Thu3 == 1 | monHoc.Thu4 == 1 | monHoc.Thu5 == 1 | monHoc.Thu6 == 1 | monHoc.Thu7 == 1 | monHoc.Cn == 1) {
                                mangMonhoc.add(monHoc);
                            }
                       // }
                    }
                    lvlichgd.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        btnsang.setOnClickListener(v -> {
            thoigianday="sang";
            btnsang.setBackgroundColor(Color.parseColor("#4CAF50"));
            btntoi.setBackgroundColor(Color.parseColor("#3F51B5"));
            btnchieu.setBackgroundColor(Color.parseColor("#3F51B5"));
            mangMonhoc.removeAll(mangMonhoc);
            kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        MonHoc monHoc = ds.getValue(MonHoc.class);
                        //if(tk.equals(monHoc.Sdtgiangvien)) {
                            if (monHoc.Thu2 == 1 | monHoc.Thu3 == 1 | monHoc.Thu4 == 1 | monHoc.Thu5 == 1 | monHoc.Thu6 == 1 | monHoc.Thu7 == 1 | monHoc.Cn == 1) {
                                mangMonhoc.add(monHoc);
                            }
                        //}
                    }
                    lvlichgd.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } );
        btnchieu.setOnClickListener(v -> {
            thoigianday="chieu";
            btnchieu.setBackgroundColor(Color.parseColor("#4CAF50"));
            btntoi.setBackgroundColor(Color.parseColor("#3F51B5"));
            btnsang.setBackgroundColor(Color.parseColor("#3F51B5"));
            mangMonhoc.removeAll(mangMonhoc);
            kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        MonHoc monHoc = ds.getValue(MonHoc.class);
                        //if(tk.equals(monHoc.Sdtgiangvien)) {
                            if (monHoc.Thu2 == 2 | monHoc.Thu3 == 2 | monHoc.Thu4 == 2 | monHoc.Thu5 == 2 | monHoc.Thu6 == 2 | monHoc.Thu7 == 2 | monHoc.Cn == 2) {
                                mangMonhoc.add(monHoc);
                            }
                        //}
                    }
                    lvlichgd.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
        btntoi.setOnClickListener(v -> {
            thoigianday="toi";
            btntoi.setBackgroundColor(Color.parseColor("#4CAF50"));
            btnsang.setBackgroundColor(Color.parseColor("#3F51B5"));
            btnchieu.setBackgroundColor(Color.parseColor("#3F51B5"));
            mangMonhoc.removeAll(mangMonhoc);
            kdata.child("MonHoc").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        MonHoc monHoc = ds.getValue(MonHoc.class);
                        //if(tk.equals(monHoc.Sdtgiangvien)) {
                            if (monHoc.Thu2 == 3 | monHoc.Thu3 == 3 | monHoc.Thu4 == 3 | monHoc.Thu5 == 3 | monHoc.Thu6 == 3 | monHoc.Thu7 == 3 | monHoc.Cn == 3) {
                                mangMonhoc.add(monHoc);
                            }
                        //}
                    }
                    lvlichgd.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });



        btntuansau.setOnClickListener(v -> {
            calendar1.add(Calendar.DATE,-calendar1.get(Calendar.DAY_OF_WEEK) + 2 + 7);
            calendar2.add(Calendar.DATE,-calendar2.get(Calendar.DAY_OF_WEEK) + 8);
            ngaybd.setText(dinhdangngay.format(calendar1.getTime()));
            ngaykt.setText(dinhdangngay.format(calendar2.getTime()));
        });
        btntuantruoc.setOnClickListener(v -> {
            calendar1.add(Calendar.DATE,-calendar1.get(Calendar.DAY_OF_WEEK)+2-7);
            calendar2.add(Calendar.DATE,-calendar2.get(Calendar.DAY_OF_WEEK)+8-7*2);
            ngaybd.setText(dinhdangngay.format(calendar1.getTime()));
            ngaykt.setText(dinhdangngay.format(calendar2.getTime()));
        });
    }
    private void Anhxa(){
        kdata = FirebaseDatabase.getInstance().getReference();
        kdata2 = FirebaseDatabase.getInstance().getReference();
        btnsang = findViewById(R.id.Btn_sang2);
        btnchieu = findViewById(R.id.Btn_chieu2);
        btntoi = findViewById(R.id.Btn_toi2);
        ngaybd = findViewById(R.id.tv_thoigianbd2);
        ngaykt = findViewById(R.id.tv_thoigiankt2);
        btntuansau = findViewById(R.id.btntuansau2);
        btntuantruoc = findViewById(R.id.btntuantruoc2);
        lvlichgd = findViewById(R.id.Lvlichgiangday2);
        mangMonhoc = new ArrayList<MonHoc>();
    }
}