package pengyi.application.order.representation;

import java.util.Date;

/**
 * Created by pengyi on 2016/4/28.
 */
public class OrderWayPointRepresentation {

    private double lat;
    private double lon;
    private Date uploadTime;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
