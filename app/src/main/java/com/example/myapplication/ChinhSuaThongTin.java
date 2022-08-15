package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ChinhSuaThongTin extends AppCompatActivity {

    ImageView Hinhdaidien;
    TextView ten, goitinh, kieutk, email;
    Button Thayhinh, Doimk, Thoat;
    FirebaseStorage mystorage = FirebaseStorage.getInstance();
    int REQUEST_CODE_IMAGE = 1;
    DatabaseReference kdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin);
        Anhxa();
        Intent nhandl = getIntent();
        String hovaten = nhandl.getStringExtra("hoten");
        String gioitinhu = nhandl.getStringExtra("gioitinh");
        String kieutaikhoan = nhandl.getStringExtra("kieutk");
        String emaila = nhandl.getStringExtra("email");
        String keyid2 = nhandl.getStringExtra("keyid");
        String taikhoan2 = nhandl.getStringExtra("taikhoan");

        ten.setText(hovaten);
        goitinh.setText(gioitinhu);
        kieutk.setText(kieutaikhoan);
        email.setText(emaila);
        Thayhinh.setOnClickListener(v -> {
            Dialogthayhinh();
        });
        kdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    users user = ds.getValue(users.class);
                    if(user.taikhoan.equals(taikhoan2)){
                        if(!user.Hinhanh.equals("no")){
                            new LoadImg().execute(user.Hinhanh);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void Anhxa(){
        Hinhdaidien = findViewById(R.id.HinhDaiDien);
        ten = findViewById(R.id.Ten);
        goitinh = findViewById(R.id.GioiTinh);
        kieutk = findViewById(R.id.kieutk);
        email = findViewById(R.id.Email);
        Thayhinh = findViewById(R.id.ThayDoiHinhDaiDien);
        Doimk = findViewById(R.id.DoiMatKhau);
        Thoat = findViewById(R.id.Thoat);
        kdata = FirebaseDatabase.getInstance().getReference("users");
    }
    private void Dialogthayhinh(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_thayhinhdaidien);
        Button chupanh = dialog.findViewById(R.id.CA);
        Button chonanh = dialog.findViewById(R.id.CTT);
        chupanh.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE_IMAGE);
        });

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Hinhdaidien.setImageBitmap(bitmap);
            Intent nhandl2 = getIntent();
            String sdt = nhandl2.getStringExtra("taikhoan");
            String keyid = nhandl2.getStringExtra("keyid");
            StorageReference storageRef = mystorage.getReference();
            StorageReference mountainsRef = storageRef.child(sdt + ".png");
            Hinhdaidien.setDrawingCacheEnabled(true);
            Hinhdaidien.buildDrawingCache();
            Bitmap bitmap2 = ((BitmapDrawable) Hinhdaidien.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data2 = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(data2);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    Toast.makeText(ChinhSuaThongTin.this,"Lỗi!!!",Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    Uri downloadUrl = urlTask.getResult();
                   kdata.child(keyid).child("Hinhanh").setValue(downloadUrl+"");
                    Toast.makeText(ChinhSuaThongTin.this,"Thành công",Toast.LENGTH_SHORT).show();
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private class LoadImg extends AsyncTask<String, Void, Bitmap>{
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
            Hinhdaidien.setImageBitmap(bitmap);
        }
    }

}