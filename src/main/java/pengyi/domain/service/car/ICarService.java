package pengyi.domain.service.car;

import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.domain.model.car.Car;
import pengyi.repository.generic.Pagination;

/**
 * Created by lvdi on 2015/3/8.
 */
public interface ICarService {

    Pagination<Car> pagination(ListCarCommand command);

    Car edit(EditCarCommand command);

    Car show(String id);

    Car searchByNumber(String carNumber);

    Car searchByDriver(String driver);

    Car create(CreateCarCommand command);


    void update(Car car);

    void delete(Car car);
}
