package pengyi.application.car.representation;

import pengyi.core.type.CarType;
import pengyi.core.type.DriverType;
import pengyi.core.type.UserType;

/**
 * Created by YJH on 2016/4/1.
 */
public class ApiAppCarRepresentation {

    private String id;
    private Integer version;

    private String name;                //车辆名称
    private String carNumber;           //车牌号

    private String userName;            //司机用户名
    private DriverType driverType;
    private UserType userType;

    private CarType carType;                      //类型

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
