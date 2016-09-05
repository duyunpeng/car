package pengyi.application.car.command;

import org.hibernate.validator.constraints.NotEmpty;
import pengyi.core.type.CarType;
import pengyi.domain.model.user.driver.Driver;

import javax.validation.constraints.NotNull;

/**
 * Created by lvdi on 2016/3/8.
 */
public class CreateCarCommand {

    @NotEmpty(message = "{car.name.NotEmpty.message}")
    private String name;                //车辆名称

    @NotEmpty(message = "{car.carNumber.NotEmpty.message}")
    private String carNumber;           //车牌号

    @NotEmpty(message = "{car.driver.Notnull.message}")
    private String driver;              //司机

  //  @NotNull(message = "{car.carType.NotEmpty.message}")
    private CarType carType;             //车辆类型

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
