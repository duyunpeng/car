package pengyi.application.car.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.car.representation.ApiAppCarRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.car.Car;

/**
 * Created by YJH on 2016/4/1.
 */
@Component
public class ApiAppCarRepresentationMapper extends CustomMapper<Car, ApiAppCarRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Car car, ApiAppCarRepresentation representation, MappingContext context) {
        if (null != car.getDriver()) {
            DriverRepresentation data = mappingService.map(car.getDriver(), DriverRepresentation.class, false);
            representation.setUserName(data.getUserName());
            representation.setDriverType(data.getDriverType());
            representation.setUserType(data.getUserType());
        }
    }

}
