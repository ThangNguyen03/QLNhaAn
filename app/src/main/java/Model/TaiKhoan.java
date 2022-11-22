package Model;

public class TaiKhoan {
    public String email,password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
