package pengyi.application.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.car.Car;
import pengyi.domain.service.car.ICarService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("carAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CarAppService implements ICarAppService {

    @Autowired
    private ICarService carService;

    @Autowired
    private IMappingService mappingService;


    @Override
    @Transactional(readOnly = true)
    public Pagination<CarRepresentation> pagination(ListCarCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Car> pagination = carService.pagination(command);
        List<CarRepresentation> data = mappingService.mapAsList(pagination.getData(), CarRepresentation.class);
        return new Pagination<CarRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public CarRepresentation create(CreateCarCommand command) {
        return mappingService.map(carService.create(command), CarRepresentation.class, false);
    }

    @Override
    public CarRepresentation edit(EditCarCommand command) {
        return mappingService.map(carService.edit(command), CarRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public CarRepresentation show(String id) {
        return mappingService.map(carService.show(id), CarRepresentation.class, false);
    }

    @Override
    public CarRepresentation searchByDriver(String driverId) {
        Car car = carService.searchByDriver(driverId);
        return car != null ? mappingService.map(car, CarRepresentation.class, false) : null;
    }

}
