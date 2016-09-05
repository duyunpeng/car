package pengyi.application.car.command;

import pengyi.core.commons.command.BasicPaginationCommand;
import pengyi.core.type.CarType;

/**
 * Created by lvdi on 2016/3/7.
 */
public class ListCarCommand extends BasicPaginationCommand {


    private String name;                //车辆名称
    private String carNumber;           //车牌号
    private String driver;              //司机
    private CarType carType;           //类型

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

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
