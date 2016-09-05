package pengyi.application.rescue.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.area.representation.AreaRepresentation;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.rescue.Rescue;

/**
 * Created by LvDi on 2016/3/9.
 */
@Component
public class RescueRepresentionMapper extends CustomMapper<Rescue,RescueRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Rescue rescue, RescueRepresentation representation, MappingContext context) {

        if(null!=rescue.getApplyUser()){
            BaseUserRepresentation applyuser = mappingService.map(rescue.getApplyUser(),BaseUserRepresentation.class,false);
            representation.setApplyUser(applyuser);
        }
        if(null!=rescue.getDriver()){
            DriverRepresentation driver = mappingService.map(rescue.getDriver(),DriverRepresentation.class,false);
            representation.setDriver(driver);
        }
        if (null != rescue.getArea()) {
            AreaRepresentation area = mappingService.map(rescue.getArea(), AreaRepresentation.class, false);
            representation.setArea(area);
        }

    }


}
