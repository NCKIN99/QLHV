package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Thanhtoangiangvien_Adapter_Admin extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<users> arrayMonHoc;
    DatabaseReference kdata;
    public Thanhtoangiangvien_Adapter_Admin(Context context, int layout, List<users> monHocList){
        myContext = context;
        myLayout = layout;
        arrayMonHoc = monHocList;
    }
    @Override
    public int getCount() {
        return arrayMonHoc.size();
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
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);
        kdata = FirebaseDatabase.getInstance().getReference();

        TextView txtstt = convertView.findViewById(R.id.tvstt_ttgv);
        txtstt.setText(String.valueOf(position+1));

        TextView txtMonHoc = convertView.findViewById(R.id.tvmamon_ttgv);
        txtMonHoc.setText(arrayMonHoc.get(position).taikhoan);
        TextView txtMamon = convertView.findViewById(R.id.tvtenmon_ttgv);
        txtMamon.setText(String.valueOf(arrayMonHoc.get(position).hoten));
        TextView trangthai = convertView.findViewById(R.id.tvtrangthai_ttgv);
        kdata.child("Luong_teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Dongtien_User dongtien_user = ds.getValue(Dongtien_User.class);
                    if(dongtien_user.Dongtien==0){
                        trangthai.setText("Chưa");
                    }
                    else if(dongtien_user.Dongtien==dongtien_user.Tienmonhoc){
                        trangthai.setText("Đã");
                    }
                    else{
                        trangthai.setText("Thiếu");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return convertView;
    }
}
