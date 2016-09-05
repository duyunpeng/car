package pengyi.application.order.representation;

import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.type.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/3/8.
 */
public class OrderRepresentation {

    private String id;
    private Integer version;

    private String orderNumber;                         //订单号
    private BaseUserRepresentation orderUser;                         //下单人
    private String createDate;                          //下单时间
    private BaseUserRepresentation receiveUser;                       //接单人
    private String receiveDate;                         //接单时间
    private String subscribeDate;                       //预约时间
    private String beginTime;                           //开始时间
    private DriverType driverType;                    //类型
    private String endTime;                             //订单完成时间
    private BigDecimal shouldMoney;                     //应付
    private BigDecimal extraMoney;                      //调度费
    private String payTime;                             //支付时间
    private OrderStatus orderStatus;                  //订单状态
    private EvaluateStatus evaluateStatus;            //评价状态
    private String startAddress;                        //开始地址
    private String endAddress;                        //结束地址
    private CarType carType;                            //车辆类型
    private PayType payType;                             //支付方式
    private String payNo;                               //支付订单号
    private Integer km;                                 //公里数
    private double startLon;                           //开始经度
    private double startLat;                            //开始纬度
    private double endLon;                           //开始经度
    private double endLat;                            //开始纬度
    private String contactPhone;                        //为别人叫代驾被联系人电话号码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BaseUserRepresentation getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(BaseUserRepresentation orderUser) {
        this.orderUser = orderUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BaseUserRepresentation getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(BaseUserRepresentation receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
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

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
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
