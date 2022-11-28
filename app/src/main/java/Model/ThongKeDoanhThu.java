package Model;

import java.util.HashMap;
import java.util.Map;

public class ThongKeDoanhThu {
    public String trangthaitt;
    public String ngaytt;
    public String tenbantt;
    public int tongtientt;

    public String getTrangthaitt() {
        return trangthaitt;
    }

    public void setTrangthaitt(String trangthaitt) {
        this.trangthaitt = trangthaitt;
    }

    public String getNgaytt() {
        return ngaytt;
    }

    public void setNgaytt(String ngaytt) {
        this.ngaytt = ngaytt;
    }

    public String getTenbantt() {
        return tenbantt;
    }

    public void setTenbantt(String tenbantt) {
        this.tenbantt = tenbantt;
    }

    public int getTongtientt() {
        return tongtientt;
    }

    public void setTongtientt(int tongtientt) {
        this.tongtientt = tongtientt;
    }

    public ThongKeDoanhThu() {
    }

    public ThongKeDoanhThu(String trangthaitt, String ngaytt, String tenbantt, int tongtientt) {
        this.trangthaitt = trangthaitt;
        this.ngaytt = ngaytt;
        this.tenbantt = tenbantt;
        this.tongtientt = tongtientt;
    }
    public Map<String,Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put("trangthaitt",trangthaitt);
        result.put("ngaytt",ngaytt);
        result.put("tenbantt",tenbantt);
        result.put("tongtientt",tongtientt);
        return result;

    }
}
