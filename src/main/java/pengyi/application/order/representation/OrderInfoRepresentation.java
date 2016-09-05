package pengyi.application.order.representation;

import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.core.type.PayType;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/4/12.
 */
public class OrderInfoRepresentation {

    private String id;
    private Integer version;

    private String orderNumber;                         //订单号

    private String driverId;                    //司机ID
    private String driverUserName;              //司机手机号
    private String driverName;                  //司机昵称
    private String driverHeadPic;               //司机头像
    private DriverType driverType;              //司机类型
    private CarType carType;                    //车辆类型
    private String carNumber;                   //车牌号
    private PayType payType;                    //支付放肆
    private BigDecimal shouldMoney;             //字符金额
    private EvaluateRepresentation evaluate;    //评价信息

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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDriverUserName() {
        return driverUserName;
    }

    public void setDriverUserName(String driverUserName) {
        this.driverUserName = driverUserName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverHeadPic() {
        return driverHeadPic;
    }

    public void setDriverHeadPic(String driverHeadPic) {
        this.driverHeadPic = driverHeadPic;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public BigDecimal getShouldMoney() {
        return shouldMoney;
    }

    public void setShouldMoney(BigDecimal shouldMoney) {
        this.shouldMoney = shouldMoney;
    }

    public EvaluateRepresentation getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(EvaluateRepresentation evaluate) {
        this.evaluate = evaluate;
    }
}
