package Model;

import java.util.HashMap;
import java.util.Map;

public class ThucDon {
    public String  matd;
    public String tentd;
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

    public ThucDon(String matd, String tentd, String anhtd) {
        this.matd = matd;
        this.tentd = tentd;
        this.anhtd = anhtd;
    }


    public ThucDon() {
    }

    public ThucDon(String tentd, String anhtd) {
        this.tentd = tentd;
        this.anhtd = anhtd;
    }
    public Map<String,Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put("matd",matd);
        result.put("manv",tentd);
        result.put("tennv",anhtd);
        return result;

    }
}
