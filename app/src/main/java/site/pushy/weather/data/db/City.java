package site.pushy.weather.data.db;

import org.litepal.crud.LitePalSupport;

public class City extends LitePalSupport {

    private int id;

    private int code;

    private String name;

    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }
}
