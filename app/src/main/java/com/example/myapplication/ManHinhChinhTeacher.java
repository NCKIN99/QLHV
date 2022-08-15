package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ManHinhChinhTeacher extends AppCompatActivity {

    ImageView mongiangday, lichgiangday, quanlyhv, taichinh, img;
    Intent truyendl, truyendl2, truyendl3;
    TextView tvhello, hoten, sdt, gioitinh1;
    DatabaseReference kdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manh_hinh_chinh_teacher);
        mongiangday = findViewById(R.id.quanlymonhoc);
        tvhello = findViewById(R.id.hello);
        hoten = findViewById(R.id.tvhoten);
        sdt = findViewById(R.id.tvsdt);
        gioitinh1 = findViewById(R.id.tvgioitinh);
        lichgiangday = findViewById(R.id.Imglichgiangday);
        quanlyhv = findViewById(R.id.imgquanlyhocvien);
        taichinh = findViewById(R.id.img_taichinh_teacher);
        img = findViewById(R.id.imageView5);
        kdata = FirebaseDatabase.getInstance().getReference("users");

        Intent nhandl = getIntent();
        String ht = nhandl.getStringExtra("hoten");
        String taikhoan = nhandl.getStringExtra("taikhoan");
        String email = nhandl.getStringExtra("email");
        String gioitinh = nhandl.getStringExtra("gioitinh");
        String kieutk = nhandl.getStringExtra("kieutk");
        String keyid = nhandl.getStringExtra("keyid");

        tvhello.setText("Xin chào! "+ht);
        hoten.setText(ht);
        sdt.setText(taikhoan);
        gioitinh1.setText(gioitinh);
        truyendl = new Intent(ManHinhChinhTeacher.this, MonGiangDay_teacher.class);
        truyendl.putExtra("hoten",ht);
        truyendl.putExtra("taikhoan",taikhoan);
        truyendl2 = new Intent(ManHinhChinhTeacher.this, quanlysinhvien_teacher.class);
        truyendl2.putExtra("taikhoan",taikhoan);
        kdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    users user = ds.getValue(users.class);
                    if(user.taikhoan.equals(taikhoan)){
                        if(!user.Hinhanh.equals("no")){
                            new ManHinhChinhTeacher.LoadImgad().execute(user.Hinhanh);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mongiangday.setOnClickListener(v -> startActivity(truyendl));
        lichgiangday.setOnClickListener(v ->{
            Intent i = new Intent(ManHinhChinhTeacher.this,Lichgiangday_teacher.class);
            i.putExtra("taikhoan",taikhoan);
            startActivity(i);
        });
        quanlyhv.setOnClickListener(v -> startActivity(truyendl2));
        taichinh.setOnClickListener(v -> {
            Intent i2 = new Intent(ManHinhChinhTeacher.this, Tai_chinh_Teacher.class);
            i2.putExtra("taikhoan",taikhoan);
            startActivity(i2);
        });
        img.setOnClickListener(v->{
            Intent i2 = new Intent(ManHinhChinhTeacher.this,ChinhSuaThongTin.class);
            i2.putExtra("hoten",ht);
            i2.putExtra("email",email);
            i2.putExtra("gioitinh",gioitinh);
            i2.putExtra("kieutk",kieutk);
            i2.putExtra("taikhoan",taikhoan);
            i2.putExtra("keyid",keyid);
            startActivity(i2);
        });
    }
    private class LoadImgad extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmaphinh = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmaphinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmaphinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
        }
    }
}