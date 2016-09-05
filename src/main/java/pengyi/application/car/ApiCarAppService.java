package pengyi.application.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.car.command.CreateCarCommand;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.car.representation.ApiAppCarRepresentation;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.car.Car;
import pengyi.domain.service.car.ICarService;

/**
 * Created by LvDi on 2016/3/16.
 */

@Service("apiCarAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiCarAppService implements IApiCarAppService {

    @Autowired
    private ICarService carService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse getByDriver(String id) {
        if (CoreStringUtils.isEmpty(id)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        Car car = carService.searchByDriver(id);
        CarRepresentation data = null;
        if (null != car) {
            data = mappingService.map(car, CarRepresentation.class, false);
        }

        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse apiCreate(CreateCarCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10002.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getCarNumber())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_30001.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getDriver())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10009.getMessage());
            }
//            if (null == command.getCarType()) {
//                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_30002.getMessage());
//            }
            CarRepresentation carRepresentation = mappingService.map(carService.create(command), CarRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, carRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse updateCar(EditCarCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getName())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10002.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getCarNumber())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_30001.getMessage());
            }
            CarRepresentation carRepresentation = mappingService.map(carService.edit(command), CarRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, carRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }

    @Override
    public BaseResponse apiInfo(String driverId) {
        if (!CoreStringUtils.isEmpty(driverId)) {
            Car car = carService.searchByDriver(driverId);
            if (car != null) {
                ApiAppCarRepresentation date = mappingService.map(car, ApiAppCarRepresentation.class, false);
                return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, date, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
            } else {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
            }
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
    }

    @Override
    public BaseResponse searchByID(String id) {
        if (!CoreStringUtils.isEmpty(id)) {
            CarRepresentation data = mappingService.map(carService.show(id), CarRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
    }
}
