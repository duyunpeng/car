package pengyi.domain.model.order;

import pengyi.domain.model.base.Identity;

import java.util.Date;

/**
 * Created by pengyi on 2016/4/27.
 */
public class OrderWayPoint extends Identity {

    private Order order;
    private double lat;
    private double lon;
    private Date uploadTime;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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

    public OrderWayPoint() {
    }

    public OrderWayPoint(Order order, double lat, double lon, Date uploadTime) {
        this.order = order;
        this.lat = lat;
        this.lon = lon;
        this.uploadTime = uploadTime;
    }
}
