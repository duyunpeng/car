package pengyi.domain.model.order;

import pengyi.core.type.*;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.report.Report;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * Created by pengyi on 2016/3/4.
 */
public class Order extends Identity {

    private String orderNumber;                         //订单号
    private BaseUser orderUser;                         //下单人
    private Date createDate;                          //下单时间
    private Driver receiveUser;                       //接单人
    private Date receiveDate;                         //接单时间
    private Date subscribeDate;                       //预约时间
    private Date beginTime;                           //开始时间
    private DriverType driverType;                    //类型
    private Date endTime;                             //订单完成时间
    private BigDecimal shouldMoney;                     //应付
    private BigDecimal extraMoney;                      //调度费
    private Date payTime;                             //支付时间
    private OrderStatus orderStatus;                  //订单状态
    private EvaluateStatus evaluateStatus;            //评价状态
    private String startAddress;                        //开始地址
    private String endAddress;                        //结束地址
    private CarType carType;                            //车辆类型
    private PayType payType;                             //支付方式
    private String payNo;                               //支付订单号
    private Integer km;                                 //公里
    private double startLon;                           //开始经度
    private double startLat;                            //开始纬度
    private double endLon;                           //开始经度
    private double endLat;                            //开始纬度
    private String contactPhone;                        //为别人叫代驾被联系人电话号码

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BaseUser getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(BaseUser orderUser) {
        this.orderUser = orderUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Driver getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(Driver receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getShouldMoney() {
        return shouldMoney;
    }

    public void setShouldMoney(BigDecimal shouldMoney) {
        this.shouldMoney = shouldMoney;
    }

    public BigDecimal getExtraMoney() {
        return extraMoney;
    }

    public void setExtraMoney(BigDecimal extraMoney) {
        this.extraMoney = extraMoney;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public EvaluateStatus getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(EvaluateStatus evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
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

    public Order() {
        super();
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
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

    public Order(String orderNumber, BaseUser orderUser, Date createDate, Driver receiveUser, Date receiveDate, Date subscribeDate, Date beginTime, DriverType driverType, Date endTime, BigDecimal shouldMoney, BigDecimal extraMoney, Date payTime, OrderStatus orderStatus, EvaluateStatus evaluateStatus, String startAddress, String endAddress, CarType carType, double startLat, double startLon, double endLat, double endLon,String contactPhone) {
        this.orderNumber = orderNumber;
        this.orderUser = orderUser;
        this.createDate = createDate;
        this.receiveUser = receiveUser;
        this.receiveDate = receiveDate;
        this.subscribeDate = subscribeDate;
        this.beginTime = beginTime;
        this.driverType = driverType;
        this.endTime = endTime;
        this.shouldMoney = shouldMoney;
        this.extraMoney = extraMoney;
        this.payTime = payTime;
        this.orderStatus = orderStatus;
        this.evaluateStatus = evaluateStatus;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.carType = carType;
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.contactPhone = contactPhone;
    }

}
