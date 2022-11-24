package Model;
public class Ban extends ThucDon {
    public int maban;
    public String tenban;
    public int soluongmon,tongtien;

    public Ban(String matd, String tentd, String giatd, String tenban, int soluongmon, int tongtien) {
        super(matd, tentd, giatd);
        this.tenban = tenban;
        this.soluongmon = soluongmon;
        this.tongtien = tongtien;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public Ban() {
    }

    public Ban(int maban, String tenban) {
        this.maban = maban;
        this.tenban = tenban;
    }
}
