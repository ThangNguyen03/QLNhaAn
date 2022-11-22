package Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class NhanVien {
public  int manv;
    public String tennv,gioitinh,ngaysinh,sdt,email,matkhau;

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public NhanVien() {
    }

    public NhanVien(String tennv, String gioitinh, String ngaysinh, String sdt, String email, String matkhau) {
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.email = email;
        this.matkhau = matkhau;
    }

    public NhanVien(int manv, String tennv, String gioitinh, String ngaysinh, String sdt, String email, String matkhau) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.email = email;
        this.matkhau = matkhau;
    }

    public Map<String,Object> toMap(){
    HashMap<String,Object> result=new HashMap<>();
    result.put("manv",manv);
    result.put("tennv",tennv);
    result.put("gioitinh",gioitinh);
    result.put("ngaysinh",ngaysinh);
    result.put("sdt",sdt);
    result.put("email",email);
    result.put("matkhau",matkhau);
    return result;

}

}
