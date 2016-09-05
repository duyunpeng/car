package pengyi.application.car;

import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.core.api.BaseResponse;


/**
 * Created by LvDi on 2016/3/16.
 */
public interface IApiCarAppService {

    BaseResponse getByDriver(String driver);

    BaseResponse apiCreate(CreateCarCommand command);

    BaseResponse updateCar(EditCarCommand command);

    BaseResponse apiInfo(String driverId);

    BaseResponse searchByID(String id);
}
