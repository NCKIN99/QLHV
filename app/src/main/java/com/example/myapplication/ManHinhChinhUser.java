package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class ManHinhChinhUser extends AppCompatActivity {

    TextView tvhello, hoten, sdt, gioitinh1;
    ImageView img_user, dkmh, monhoc, taichinh, imgtkb;
    DatabaseReference kdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh_user);
        tvhello = (TextView)findViewById(R.id.hello);
        hoten = (TextView)findViewById(R.id.tvhoten);
        sdt = (TextView)findViewById(R.id.tvsdt);
        gioitinh1 = (TextView)findViewById(R.id.tvgioitinh);
        img_user = (ImageView)findViewById(R.id.imageView3);
        dkmh = findViewById(R.id.dkmonhoc_user);
        monhoc = findViewById(R.id.monhoc_user);
        taichinh = findViewById(R.id.img_taichinh_user);
        imgtkb = findViewById(R.id.img_tkb);
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
        kdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    users user = ds.getValue(users.class);
                    if(user.taikhoan.equals(taikhoan)){
                        if(!user.Hinhanh.equals("no")){
                            new ManHinhChinhUser.LoadImgad().execute(user.Hinhanh);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dkmh.setOnClickListener(v ->{
            Intent i = new Intent(ManHinhChinhUser.this,Dangkymonhoc_User.class);
            i.putExtra("taikhoan",taikhoan);
            startActivity(i);
        });

        monhoc.setOnClickListener(v ->{
            Intent i2 = new Intent(ManHinhChinhUser.this,Monhoc_user.class);
            i2.putExtra("taikhoan",taikhoan);
            startActivity(i2);
        });
        taichinh.setOnClickListener(v ->{
            Intent i =new Intent(ManHinhChinhUser.this,Tai_chinh_User.class);
            i.putExtra("taikhoan",taikhoan);
            startActivity(i);
        });
        imgtkb.setOnClickListener(v -> {
            Intent i =new Intent(ManHinhChinhUser.this,Thoikhoabieu_user.class);
            i.putExtra("taikhoan",taikhoan);
            startActivity(i);
        });
        img_user.setOnClickListener(v->{
            Intent i2 = new Intent(ManHinhChinhUser.this,ChinhSuaThongTin.class);
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
            img_user.setImageBitmap(bitmap);
        }
    }
}