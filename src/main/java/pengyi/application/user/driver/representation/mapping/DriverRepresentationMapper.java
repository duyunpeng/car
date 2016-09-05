package pengyi.application.user.driver.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.driver.Driver;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class DriverRepresentationMapper extends CustomMapper<Driver, DriverRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Driver driver, DriverRepresentation representation, MappingContext context) {
        if (null != driver.getCompany()) {
            CompanyRepresentation data = mappingService.map(driver.getCompany(), CompanyRepresentation.class, false);
            representation.setCompany(data);
        }
    }
}
