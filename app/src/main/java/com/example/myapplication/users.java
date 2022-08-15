package com.example.myapplication;

public class users {
    public String taikhoan;
    public String matkhau;
    public String hoten;
    public String email;
    public String gioitinh;
    public String kieutk;
    public String Hinhanh;

    public users() {

    }

    public users(String taikhoan, String matkhau, String hoten, String email, String gioitinh, String kieutk, String hinhanh) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.email = email;
        this.gioitinh = gioitinh;
        this.kieutk = kieutk;
        Hinhanh = hinhanh;
    }

    @Override
    public String toString() {
        return taikhoan+"";
    }
}
