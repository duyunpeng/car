package pengyi.domain.service.car;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.car.Car;
import pengyi.domain.model.car.ICarRepository;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created lvdi on 2016/3/8.
 */
@Service("carService")
public class CarService implements ICarService {

    @Autowired
    private ICarRepository<Car, String> carRepository;
    @Autowired
    private IDriverService driverService;


    @Override
    public Pagination<Car> pagination(ListCarCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (null != command.getCarType()) {
            criteriaList.add(Restrictions.eq("carType", command.getCarType()));
        }
        if (!CoreStringUtils.isEmpty(command.getCarNumber())) {
            criteriaList.add(Restrictions.like("carNumber", command.getCarNumber()));
        }
        if (!CoreStringUtils.isEmpty(command.getDriver())) {
            criteriaList.add(Restrictions.like("driver.id", command.getDriver()));
        }
        if (!CoreStringUtils.isEmpty(command.getName())) {
            criteriaList.add(Restrictions.like("name", command.getName()));
        }
//        List<Order> orderList=new ArrayList<Order>();
//        orderList.add(Order.desc("createDate"));
        return carRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }


    /**
     * 修改车辆信息
     */
    @Override
    public Car edit(EditCarCommand command) {
        Car car = this.show(command.getId());
        car.fainWhenConcurrencyViolation(car.getVersion());
        if (!car.getCarNumber().equals(command.getCarNumber())) {
            Car car1 = this.searchByNumber(command.getCarNumber());
            if (null != car1) {
                throw new ExistException("车牌号[" + command.getCarNumber() + "]的记录已存在");
            }
            car.setCarNumber(command.getCarNumber());
        }

        car.setCarType(command.getCarType());
        car.setName(command.getName());
        carRepository.update(car);
        return car;
    }

    /**
     * 根据id查看车辆
     */
    @Override
    public Car show(String id) {
        Car car = carRepository.getById(id);
        if (null == car) {
            throw new NoFoundException("没有找到车辆id=[" + id + "]的记录");
        }
        return car;
    }

    /**
     * 根据车牌号查看车辆
     */
    @Override
    public Car searchByNumber(String carNumber) {
        return carRepository.getByNumber(carNumber);
    }

    /**
     * 根据司机查找车辆
     */
    @Override
    public Car searchByDriver(String driver) {
        return carRepository.getByDriver(driver);
    }


    /**
     * 创建车辆
     */

    @Override
    public Car create(CreateCarCommand command) {

        Car carNumberUnique = this.searchByNumber(command.getCarNumber());
        Car carDriverUnique = this.searchByDriver(command.getDriver());
        if (null != carNumberUnique) {
            throw new ExistException("车牌号[" + command.getCarNumber() + "]已存在");
        }
        if (null != carDriverUnique) {
            throw new ExistException("司机[" + carDriverUnique.getDriver().getUserName() + "]已经拥有车辆");
        }
        Driver driver = driverService.show(command.getDriver());
        Car car = new Car(command.getName(), command.getCarNumber(), driver, command.getCarType());
        carRepository.save(car);
        return car;
    }

    @Override
    public void update(Car car) {
        carRepository.update(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }


}
