package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Thoikhoabieu_Adapter_user extends BaseAdapter {
    Context mycontext;
    int mylayout;
    List<MonHoc> arraymonhoc;
    DatabaseReference kdata;
    Calendar calendar = Calendar.getInstance();
    Lichgiangday_teacher lichgiangday_teacher;
    users user;
    public Thoikhoabieu_Adapter_user(Context context, int layout, List<MonHoc> monHocList){
        mycontext = context;
        mylayout = layout;
        arraymonhoc = monHocList;
    }
    @Override
    public int getCount() {
        return arraymonhoc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(mylayout,null);

        TextView txtstt = convertView.findViewById(R.id.tvstt_lichgiangday);
        txtstt.setText(String.valueOf(position));
        TextView txtbuoiday = convertView.findViewById(R.id.tv_buoiday);
        TextView txtngayday = convertView.findViewById(R.id.tv_ngayday);
        TextView txtthongtin = convertView.findViewById(R.id.tv_thongtinmon);
        kdata = FirebaseDatabase.getInstance().getReference();
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd/MM/yyyy");
        String key = lichgiangday_teacher.key;
        /*kdata.child("users").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {

                        lichgiangday = user.Thoigianday;
                }*/
        String lichgiangday = lichgiangday_teacher.thoigianday ;//=snapshot.getValue().toString();
        if (lichgiangday.equals("sang") && arraymonhoc.size() != 0) {
            if (arraymonhoc.get(position).Thu2 == 1) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 2");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu2 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu3 == 1) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 3");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu3 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu4 == 1) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 4");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu4 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu5 == 1) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 5");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu5 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu6 == 1) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 6");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu6 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu7 == 1) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 7");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu7 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Cn == 1) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Chủ nhật");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Cn + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
        }
        if (lichgiangday.equals("chieu") && arraymonhoc.size() != 0) {
            if (arraymonhoc.get(position).Thu2 == 2) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 2");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu2 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu3 == 2) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 3");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu3 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu4 == 2) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 4");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu4 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu5 == 2) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 5");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu5 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu6 == 2) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 6");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu6 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu7 == 2) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 7");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu7 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Cn == 2) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Chủ nhật");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Cn + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }

        }
        if (lichgiangday.equals("toi") && arraymonhoc.size() != 0) {
            if (arraymonhoc.get(position).Thu2 == 3) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 2");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu2 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu3 == 3) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 3");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu3 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu4 == 3) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 4");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu4 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu5 == 3) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 5");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu5 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu6 == 3) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 6");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu6 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Thu7 == 3) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Thứ 7");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Thu7 + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }
            if (arraymonhoc.get(position).Cn == 3) {
                txtngayday.setText(dinhdangngay.format(calendar.getTime()));
                txtstt.setText(String.valueOf(position + 1));
                txtbuoiday.setText("Chủ nhật");
                txtthongtin.setText("Ca " + arraymonhoc.get(position).Cn + "\n" + arraymonhoc.get(position).Mamonhoc + "\n" + arraymonhoc.get(position).TenMon + "\n" + "GV: " + arraymonhoc.get(position).Giangvienday + "(" + arraymonhoc.get(position).Sdtgiangvien + ")");
            }

        }

        return convertView;
    }
}
