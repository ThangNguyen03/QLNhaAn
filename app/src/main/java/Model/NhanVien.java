package Model;

public class NhanVien {
    public String manv,tennv,gioitinh,ngaysinh,email,matkhau;

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
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

    public NhanVien() {
    }

    public NhanVien(String manv, String tennv, String gioitinh, String ngaysinh, String email, String matkhau) {
        this.manv = manv;
        this.tennv = tennv;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.email = email;
        this.matkhau = matkhau;
    }
}
