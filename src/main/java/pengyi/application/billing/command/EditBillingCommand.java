package pengyi.application.billing.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.core.type.EnableStatus;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by YJH on 2016/3/21.
 */
public class EditBillingCommand extends SharedCommand{

    private String id;
    private Integer version;

    @NotNull(message = "{billing.kmBilling.NotNull.message}")
    private BigDecimal kmBilling;
    @NotNull(message = "{billing.minuteBilling.NotNull.message}")
    private BigDecimal minuteBilling;
    @NotNull(message = "{billing.startingPrice.NotNull.message}")
    private BigDecimal startingPrice;   //起步价
    @NotEmpty(message = "{billing.company.NotEmpty.message}")
    private String company;  //公司
    @NotNull(message = "{billing.startKm.NotNull.message}")
    private Integer startKm;
    @NotNull(message = "{billing.startMin.NotNull.message}")



    private Integer startMin;

    private DriverType driverType;
    private CarType carType;

    @NotNull(message = "{billing.status.NotNull.messages}")
    private EnableStatus status;

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

    public BigDecimal getKmBilling() {
        return kmBilling;
    }

    public void setKmBilling(BigDecimal kmBilling) {
        this.kmBilling = kmBilling;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getMinuteBilling() {
        return minuteBilling;
    }

    public void setMinuteBilling(BigDecimal minuteBilling) {
        this.minuteBilling = minuteBilling;
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

    public void changeStatus(EnableStatus status){this.status=status;}
    private void setStatus(EnableStatus status) {
        this.status = status;
    }
}
