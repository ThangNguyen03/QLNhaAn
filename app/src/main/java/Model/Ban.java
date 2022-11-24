package Model;

public class Ban {
    public int maban;
    public String tenban;

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
