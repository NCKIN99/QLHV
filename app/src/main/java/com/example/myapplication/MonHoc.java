package com.example.myapplication;

public class MonHoc {
    public int Mamonhoc;
    public String TenMon;
    public String Thoigianday;
    public String SoTiet;
    public String Giangvienday;
    public String Sdtgiangvien;
    public String Trangthai;
    public String Giamonhoc;
    public int Thu2;
    public int Thu3;
    public int Thu4;
    public int Thu5;
    public int Thu6;
    public int Thu7;
    public int Cn;
    public int Ngaybatdau;
    public int Thangbatdau;
    public int Nambatdau;
    public String Syso;



    public MonHoc() {

    }

    public MonHoc(int mamonhoc, String tenMon, String thoigianday, String soTiet, String giangvienday, String sdtgiangvien, String trangthai, String giamonhoc, int thu2, int thu3, int thu4, int thu5, int thu6, int thu7, int cn, int ngaybatdau, int thangbatdau, int nambatdau, String syso) {
        Mamonhoc = mamonhoc;
        TenMon = tenMon;
        Thoigianday = thoigianday;
        SoTiet = soTiet;
        Giangvienday = giangvienday;
        Sdtgiangvien = sdtgiangvien;
        Trangthai = trangthai;
        Giamonhoc = giamonhoc;
        Thu2 = thu2;
        Thu3 = thu3;
        Thu4 = thu4;
        Thu5 = thu5;
        Thu6 = thu6;
        Thu7 = thu7;
        Cn = cn;
        Ngaybatdau = ngaybatdau;
        Thangbatdau = thangbatdau;
        Nambatdau = nambatdau;
        Syso = syso;
    }

    @Override
    public String toString() {
        return Mamonhoc+"";
    }
}
