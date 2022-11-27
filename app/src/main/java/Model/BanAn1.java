package Model;

public class BanAn1 {
    public String tentd;
    public  int giatd, sltd,tongtientd,tongtienthanhtoan;

    public int getTongtienthanhtoan() {
        return tongtienthanhtoan;
    }

    public void setTongtienthanhtoan(int tongtienthanhtoan) {
        this.tongtienthanhtoan = tongtienthanhtoan;
    }

    public String getTentd() {
        return tentd;
    }

    public void setTentd(String tentd) {
        this.tentd = tentd;
    }

    public int getGiatd() {
        return giatd;
    }

    public void setGiatd(int giatd) {
        this.giatd = giatd;
    }

    public int getSltd() {
        return sltd;
    }

    public void setSltd(int sltd) {
        this.sltd = sltd;
    }

    public int getTongtientd() {
        return tongtientd;
    }

    public void setTongtientd(int tongtientd) {
        this.tongtientd = tongtientd;
    }

    public BanAn1() {
    }

    public BanAn1(int giatd, int sltd, int tongtientd) {
        this.giatd = giatd;
        this.sltd = sltd;
        this.tongtientd = tongtientd;
    }

    public BanAn1(String tentd, int giatd, int sltd, int tongtientd) {
        this.tentd = tentd;
        this.giatd = giatd;
        this.sltd = sltd;
        this.tongtientd = tongtientd;
    }
}
