package Model;

import java.util.HashMap;
import java.util.Map;

public class ThucDon {
    public String  matd;
    public String tentd;
    public String giatd;

    public ThucDon(String matd, String tentd, String giatd, String anhtd) {
        this.matd = matd;
        this.tentd = tentd;
        this.giatd = giatd;
        this.anhtd = anhtd;
    }

    public String getGiatd() {
        return giatd;
    }

    public void setGiatd(String giatd) {
        this.giatd = giatd;
    }

    public String anhtd;

    public String getTentd() {
        return tentd;
    }

    public void setTentd(String tentd) {
        this.tentd = tentd;
    }

    public String getAnhtd() {
        return anhtd;
    }

    public void setAnhtd(String anhtd) {
        this.anhtd = anhtd;
    }

    public String getMatd() {
        return matd;
    }

    public void setMatd(String matd) {
        this.matd = matd;
    }



    public ThucDon() {
    }

    public ThucDon(String matd, String tentd, String giatd) {
        this.matd = matd;
        this.tentd = tentd;
        this.giatd = giatd;
    }
}
