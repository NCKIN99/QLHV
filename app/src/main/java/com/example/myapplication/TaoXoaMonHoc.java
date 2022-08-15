package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class TaoXoaMonHoc extends AppCompatActivity {

    LinearLayout ln1;
    DatabaseReference kdata, Count_Monhoc, dx;
    EditText giangvienday, sotiethoc, Tenmonhoc, xoaidmonhoc, giamonhoc, sdtgv, syso;
    Button taomonhoc, xoamonhoc, taolichhoc, chonngay;
    TextView ngaybatdau;
    String thoigianhoc;
    int day2;
    int year2;
    int month2;
    MonHoc monHoc;
    int dem=0;
    int demxoa = 0;
    int dem_xoa=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_xoa_mon_hoc);
        Anhxa();
        Intent nhandl = getIntent();
        String lht2 = nhandl.getStringExtra("th2bd");
        String lht3 = nhandl.getStringExtra("th3bd");
        String lht4 = nhandl.getStringExtra("th4bd");
        String lht5 = nhandl.getStringExtra("th5bd");
        String lht6 = nhandl.getStringExtra("th6bd");
        String lht7 = nhandl.getStringExtra("th7bd");
        String lhcn = nhandl.getStringExtra("cnbd");

        if(lht2==null)
            lht2="0";
        if(lht3==null)
            lht3="0";
        if(lht4==null)
            lht4="0";
        if(lht5==null)
            lht5="0";
        if(lht6==null)
            lht6="0";
        if(lht7==null)
            lht7="0";
        if(lhcn==null)
            lhcn="0";

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONDAY);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

            if (Integer.parseInt(lht2)!=0|Integer.parseInt(lht3)!=0|Integer.parseInt(lht4)!=0|Integer.parseInt(lht5)!=0|Integer.parseInt(lht6)!=0|Integer.parseInt(lht7)!=0|Integer.parseInt(lhcn)!=0) {
                taolichhoc.setBackgroundColor(Color.parseColor("#28E12F"));
                ln1.setBackgroundResource(R.drawable.check);
                String ngiahp = nhandl.getStringExtra("giahp");
                String ntengv = nhandl.getStringExtra("tengiangvien");
                String nsotiethoc = nhandl.getStringExtra("sotiethoc");
                String ntenmonhoc = nhandl.getStringExtra("tenmonhoc");
                String nsdtgv = nhandl.getStringExtra("sodienthoaigv");
                String nsyso = nhandl.getStringExtra("sysolophoc");
                giangvienday.setText(ntengv);
                sotiethoc.setText(nsotiethoc);
                Tenmonhoc.setText(ntenmonhoc);
                giamonhoc.setText(ngiahp);
                sdtgv.setText(nsdtgv);
                syso.setText(nsyso);
            } else {
                taolichhoc.setBackgroundColor(Color.parseColor("#E60505"));
                taolichhoc.setTextColor(Color.parseColor("#3F51B5"));
                String ngiahp = nhandl.getStringExtra("giahp");
                String ntengv = nhandl.getStringExtra("tengiangvien");
                String nsotiethoc = nhandl.getStringExtra("sotiethoc");
                String ntenmonhoc = nhandl.getStringExtra("tenmonhoc");
                String nsdtgv = nhandl.getStringExtra("sodienthoaigv");
                String nsyso = nhandl.getStringExtra("sysolophoc");
                giangvienday.setText(ntengv);
                sotiethoc.setText(nsotiethoc);
                Tenmonhoc.setText(ntenmonhoc);
                giamonhoc.setText(ngiahp);
                sdtgv.setText(nsdtgv);
                syso.setText(nsyso);
            }
        chonngay.setOnClickListener( v ->{
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    TaoXoaMonHoc.this, (view, year1, month1, day1) -> {
                        month1 = month1 + 1;
                        String date = day1 + "/" + month1 + "/" + year1;
                        day2 = day1;
                        month2 = month1;
                        year2 = year1;
                        ngaybatdau.setText(date);
                    },year,month,day );
            datePickerDialog.show();
        });
        xoamonhoc.setOnClickListener(v -> kdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(xoaidmonhoc.getText().toString().length()>0) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        MonHoc monHoc = ds.getValue(MonHoc.class);
                        if (String.valueOf(monHoc.Mamonhoc).equals(xoaidmonhoc.getText().toString())) {
                            String key_id = ds.getKey();
                            kdata.child(key_id).removeValue((error, ref) -> Toast.makeText(TaoXoaMonHoc.this, "Xóa thành công", Toast.LENGTH_SHORT).show());
                            break;
                        }
                    }
                }
                else
                    Toast.makeText(TaoXoaMonHoc.this, "Nhập id môn học cần xóa", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));
        kdata.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                demxoa = demxoa +1;
                dx.child("dem_xoa").setValue(demxoa);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Count_Monhoc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    dem = (int)snapshot.getChildrenCount();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        dx.child("dem_xoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dem_xoa = Integer.parseInt(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        String finalLht = lht2;
        String finalLht1 = lht3;
        String finalLht2 = lht4;
        String finalLht3 = lht5;
        String finalLht4 = lht6;
        String finalLht5 = lht7;
        String finalLhcn = lhcn;
        taomonhoc.setOnClickListener(v -> {
            int mamon = 20200000+dem+dem_xoa;
            String giahp = giamonhoc.getText().toString().trim();
            String tengiangvien = giangvienday.getText().toString().trim();
            String sotiet = sotiethoc.getText().toString().trim();
            String Tenmon = Tenmonhoc.getText().toString().trim();
            String sdtofgv = sdtgv.getText().toString().trim();
            String sysolop = syso.getText().toString();
            if(giahp.length()>0&&tengiangvien.length()>0&&sotiet.length()>0&&Tenmon.length()>0 && sdtofgv.length()>0 &&sysolop.length()>0) {
                monHoc = new MonHoc(mamon, Tenmon, thoigianhoc, sotiet, tengiangvien,sdtofgv, "đang học", giahp, Integer.parseInt(finalLht), Integer.parseInt(finalLht1) , Integer.parseInt(finalLht2), Integer.parseInt(finalLht3) , Integer.parseInt(finalLht4), Integer.parseInt(finalLht5) , Integer.parseInt(finalLhcn), day2, month2, year2, sysolop);
                kdata.push().setValue(monHoc);
                Toast.makeText(TaoXoaMonHoc.this,"Tạo thành công",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(TaoXoaMonHoc.this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();

        });
        taolichhoc.setOnClickListener(v -> {
            Intent i3 = new Intent(TaoXoaMonHoc.this,Tao_Lichhoc.class);
            i3.putExtra("giahp",giamonhoc.getText().toString());
            i3.putExtra("tengiangvien",giangvienday.getText().toString());
            i3.putExtra("sotiethoc",sotiethoc.getText().toString());
            i3.putExtra("tenmonhoc",Tenmonhoc.getText().toString());
            i3.putExtra("sodienthoaigv",sdtgv.getText().toString());
            i3.putExtra("syso",syso.getText().toString());
            startActivity(i3);
            finish();
        });


    }

    private void Anhxa(){
        dx = FirebaseDatabase.getInstance().getReference();
        Count_Monhoc = FirebaseDatabase.getInstance().getReference().child("MonHoc");
        kdata = FirebaseDatabase.getInstance().getReference("MonHoc");
        giangvienday = findViewById(R.id.Edtgiangvien);
        sdtgv = findViewById(R.id.Edtsdtgiangvien);
        sotiethoc = findViewById(R.id.Edtsotiet);
        Tenmonhoc = findViewById(R.id.Edttenmon);
        giamonhoc = findViewById(R.id.Edtgiahocphan);
        xoaidmonhoc = findViewById(R.id.idxoamonhoc);
        taomonhoc = findViewById(R.id.btntaomh);
        xoamonhoc = findViewById(R.id.btnxoamh);
        taolichhoc = findViewById(R.id.btnclh);
        ln1 = findViewById(R.id.codulieu);
        chonngay = findViewById(R.id.btnchonngay);
        ngaybatdau = findViewById(R.id.tvngaybatdau);
        syso = findViewById(R.id.Edtsyso);

    }

}