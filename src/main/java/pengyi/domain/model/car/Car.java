package pengyi.domain.model.car;

import pengyi.core.type.CarType;
import pengyi.domain.model.base.Identity;
import pengyi.domain.model.user.driver.Driver;

/**
 * 车辆
 * Created by pengyi on 2016/3/4.
 */
public class Car extends Identity {

    private String name;                //车辆名称
    private String carNumber;           //车牌号
    private Driver driver;              //司机
    private CarType carType;            //类型

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }



    public Car() {
        super();
    }

    public Car(String name, String carNumber, Driver driver, CarType carType) {
        this.name = name;
        this.carNumber = carNumber;
        this.driver = driver;
        this.carType = carType;
    }
}
