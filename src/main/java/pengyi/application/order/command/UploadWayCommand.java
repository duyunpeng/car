package pengyi.application.order.command;

/**
 * Created by YJH on 2016/3/8.
 */
public class UploadWayCommand {

    private String orderId;
    private double lat;
    private double lon;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
