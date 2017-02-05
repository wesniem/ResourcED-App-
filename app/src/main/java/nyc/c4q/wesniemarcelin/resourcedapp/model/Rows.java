package nyc.c4q.wesniemarcelin.resourcedapp.model;

import java.io.Serializable;

/**
 * Created by josevila on 2/4/17.
 */
public class Rows implements Serializable {
    private String locname;
    private String address;
    private String zip;
    private String phone;
    private String borough;
    private String prektype;
    private String seats;
    private String daylength;
    private String x;
    private String y;

    public String getLocname() {
        return locname;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getBorough() {
        return borough;
    }

    public String getPrektype() {
        return prektype;
    }

    public String getSeats() {
        return seats;
    }

    public String getDaylength() {
        return daylength;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}
