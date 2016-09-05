package pengyi.application.car;

import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.command.ListCarCommand;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ICarAppService {

    Pagination<CarRepresentation> pagination(ListCarCommand command);

    CarRepresentation create(CreateCarCommand command);

    CarRepresentation edit(EditCarCommand command);

    CarRepresentation show(String id);


    CarRepresentation searchByDriver(String driverId);
}
