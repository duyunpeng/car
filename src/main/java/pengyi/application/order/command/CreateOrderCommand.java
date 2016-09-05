package pengyi.application.order.command;

import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.core.type.EvaluateStatus;
import pengyi.core.type.OrderStatus;
import pengyi.domain.model.car.Car;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/8.
 */
public class CreateOrderCommand {

    private String orderUser;                         //下单人
    private String subscribeDate;                       //预约时间
    private DriverType driverType;                    //接单司机类型类型
    private BigDecimal extraMoney;                      //调度费
    private String startAddress;                        //开始地址
    private String endAddress;                          //结束地址
    private CarType carType;                            //车辆类型
    private String drivers;                             //附近司机
    private double startLon;                           //开始经度
    private double startLat;                            //开始纬度
    private double endLon;                           //开始经度
    private double endLat;                            //开始纬度
    private String contactPhone;                        //为别人叫代驾被联系人电话号码

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public BigDecimal getExtraMoney() {
        return extraMoney;
    }

    public void setExtraMoney(BigDecimal extraMoney) {
        this.extraMoney = extraMoney;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getDrivers() {
        return drivers;
    }

    public void setDrivers(String drivers) {
        this.drivers = drivers;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getEndLon() {
        return endLon;
    }

    public void setEndLon(double endLon) {
        this.endLon = endLon;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
