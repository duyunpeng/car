package pengyi.application.car.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.car.representation.CarRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.core.type.CarType;
import pengyi.domain.model.car.Car;

/**
 * Created by lvdi on 2016/3/8.
 */
@Component
public class CarRepresentationMapper extends CustomMapper<Car, CarRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Car car,CarRepresentation representation,MappingContext context){
        if(null!=car.getDriver()){
            DriverRepresentation driver = mappingService.map(car.getDriver(),DriverRepresentation.class,false);
            representation.setDriver(driver);
        }

    }

}
