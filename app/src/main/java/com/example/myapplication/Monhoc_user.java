package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Monhoc_user extends AppCompatActivity {

    TextView btndanghoc, btndahoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monhoc_user);
        btndanghoc = findViewById(R.id.btn_mhdhhv);
        btndahoc = findViewById(R.id.btn_mhdhhv2);

        Intent nhandl = getIntent();
        String tk = nhandl.getStringExtra("taikhoan");

        Intent i1 = new Intent(Monhoc_user.this,Monhocdanghoc_user.class);
        i1.putExtra("taikhoan",tk);
        btndanghoc.setOnClickListener(v -> startActivity(i1));
    }
}