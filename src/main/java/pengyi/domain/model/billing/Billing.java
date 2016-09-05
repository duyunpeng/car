package pengyi.domain.model.billing;

import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.core.type.EnableStatus;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class Billing extends Identity {

    private BigDecimal kmBilling;   //根据公里计费

    private BigDecimal minuteBilling; //根据分钟计费

    private BigDecimal startingPrice;   //起步价

    private Integer startKm;

    private Integer startMin;

    private DriverType driverType;

    private CarType carType;

    private Company company;

    private EnableStatus status;        //状态

    public Billing() {
        super();
    }

    public Billing(BigDecimal kmBilling, BigDecimal minuteBilling,BigDecimal startingPrice, Company company, DriverType driverType, CarType carType, Integer startKm, Integer startMin,EnableStatus status) {
        this.kmBilling = kmBilling;
        this.minuteBilling = minuteBilling;
        this.startingPrice = startingPrice;
        this.company = company;
        this.driverType = driverType;
        this.carType = carType;
        this.startKm = startKm;
        this.startMin = startMin;
        this.status=status;
    }

    public BigDecimal getKmBilling() {
        return kmBilling;
    }

    public void setKmBilling(BigDecimal kmBilling) {
        this.kmBilling = kmBilling;
    }

    public BigDecimal getMinuteBilling() {
        return minuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        this.minuteBilling = minuteBilling;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Integer getStartKm() {
        return startKm;
    }

    public void setStartKm(Integer startKm) {
        this.startKm = startKm;
    }

    public Integer getStartMin() {
        return startMin;
    }

    public void setStartMin(Integer startMin) {
        this.startMin = startMin;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

}
