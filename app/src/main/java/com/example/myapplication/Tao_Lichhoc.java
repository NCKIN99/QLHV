package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Tao_Lichhoc extends AppCompatActivity {

    RadioButton c1t2, c2t2, c3t2, c1t3, c2t3, c3t3, c1t4, c2t4, c3t4, c1t5, c2t5, c3t5, c1t6, c2t6, c3t6, c1t7, c2t7, c3t7, c1cn, c2cn, c3cn;
    Button btnxong;
    Intent i;
    int rbt2, rbt3, rbt4, rbt5, rbt6, rbt7, rbcn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao__lichhoc);
        Anhxa();

        Intent laydl = getIntent();
        String lgiahp = laydl.getStringExtra("giahp");
        String ltengv = laydl.getStringExtra("tengiangvien");
        String lsotiethoc = laydl.getStringExtra("sotiethoc");
        String ltenmonhoc = laydl.getStringExtra("tenmonhoc");
        String lsdtgv = laydl.getStringExtra("sodienthoaigv");
        String lsyso = laydl.getStringExtra("syso");
        
        btnxong.setOnClickListener(v -> {
            i = new Intent(Tao_Lichhoc.this,TaoXoaMonHoc.class);
            Laydllichhoc();
            i.putExtra("th2bd",String.valueOf(rbt2));
            i.putExtra("th3bd",String.valueOf(rbt3));
            i.putExtra("th4bd",String.valueOf(rbt4));
            i.putExtra("th5bd",String.valueOf(rbt5));
            i.putExtra("th6bd",String.valueOf(rbt6));
            i.putExtra("th7bd",String.valueOf(rbt7));
            i.putExtra("cnbd",String.valueOf(rbcn));


            i.putExtra("giahp",lgiahp);
            i.putExtra("tengiangvien",ltengv);
            i.putExtra("sotiethoc",lsotiethoc);
            i.putExtra("tenmonhoc",ltenmonhoc);
            i.putExtra("sodienthoaigv",lsdtgv);
            i.putExtra("sysolophoc",lsyso);
            startActivity(i);
            finish();
        });
    }
    private void Anhxa(){
        c1t2 = findViewById(R.id.Cbca1t2);
        c2t2 = findViewById(R.id.Cbca2t2);
        c3t2 = findViewById(R.id.Cbca3t2);
        c1t3 = findViewById(R.id.Cbca1t3);
        c2t3 = findViewById(R.id.Cbca2t3);
        c3t3 = findViewById(R.id.Cbca3t3);
        c1t4 = findViewById(R.id.Cbca1t4);
        c2t4 = findViewById(R.id.Cbca2t4);
        c3t4 = findViewById(R.id.Cbca3t4);
        c1t5 = findViewById(R.id.Cbca1t5);
        c2t5 = findViewById(R.id.Cbca2t5);
        c3t5 = findViewById(R.id.Cbca3t5);
        c1t6 = findViewById(R.id.Cbca1t6);
        c2t6 = findViewById(R.id.Cbca2t6);
        c3t6 = findViewById(R.id.Cbca3t6);
        c1t7 = findViewById(R.id.Cbca1t7);
        c2t7 = findViewById(R.id.Cbca2t7);
        c3t7 = findViewById(R.id.Cbca3t7);
        c1cn = findViewById(R.id.Cbca1cn);
        c2cn = findViewById(R.id.Cbca2cn);
        c3cn = findViewById(R.id.Cbca3cn);
        btnxong = findViewById(R.id.btnxong);
    }
    private void Laydllichhoc(){
        if(c1t2.isChecked())
            rbt2= 1;
        if(c2t2.isChecked())
            rbt2= 2;
        if(c3t2.isChecked())
            rbt2= 3;
        if(c1t2.isChecked()==false && c2t2.isChecked()==false && c3t2.isChecked()==false)
            rbt2 = 0;

        if(c1t3.isChecked())
            rbt3= 1;
        if(c2t3.isChecked())
            rbt3= 2;
        if(c3t3.isChecked())
            rbt3= 3;
        if(c1t3.isChecked()==false && c2t3.isChecked()==false && c3t3.isChecked()==false)
            rbt3 = 0;

        if(c1t4.isChecked())
            rbt4= 1;
        if(c2t4.isChecked())
            rbt4= 2;
        if(c3t4.isChecked())
            rbt4 = 3;
        if(c1t4.isChecked()==false && c2t4.isChecked()==false && c3t4.isChecked()==false)
            rbt4 = 0;

        if(c1t5.isChecked())
            rbt5= 1;
        if(c2t5.isChecked())
            rbt5= 2;
        if(c3t5.isChecked())
            rbt5= 3;
        if(c1t5.isChecked()==false && c2t5.isChecked()==false && c3t5.isChecked()==false)
            rbt5 = 0;

        if(c1t6.isChecked())
            rbt6= 1;
        if(c2t6.isChecked())
            rbt6= 2;
        if(c3t6.isChecked())
            rbt6= 3;
        if(c1t6.isChecked()==false && c2t6.isChecked()==false && c3t6.isChecked()==false)
            rbt6 = 0;

        if(c1t7.isChecked())
            rbt7= 1;
        if(c2t7.isChecked())
            rbt7= 2;
        if(c3t7.isChecked())
            rbt7= 3;
        if(c1t7.isChecked()==false && c2t7.isChecked()==false && c3t7.isChecked()==false)
            rbt7 = 0;

        if(c1cn.isChecked())
            rbcn= 1;
        if(c2cn.isChecked())
            rbcn= 2;
        if(c3cn.isChecked())
            rbcn= 3;
        if(c1cn.isChecked()==false && c2cn.isChecked()==false && c3cn.isChecked()==false)
            rbcn = 0;
    }
}