package pengyi.application.billing.command;

import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;

/**
 * Created by YJH on 2016/3/29.
 */
public class SearchBillingCommand {

    private DriverType driverType;
    private CarType carType;
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
