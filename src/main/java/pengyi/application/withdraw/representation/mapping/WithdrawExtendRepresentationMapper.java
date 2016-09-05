package pengyi.application.withdraw.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.application.withdraw.representation.WithdrawExtendRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.model.withdraw.Withdraw;
import pengyi.domain.service.user.driver.IDriverService;

/**
 * Created by yjh on 16-6-6.
 */
@Component
public class WithdrawExtendRepresentationMapper extends CustomMapper<Withdraw, WithdrawExtendRepresentation> {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IDriverService driverService;

    public void mapAtoB(Withdraw withdraw, WithdrawExtendRepresentation representation, MappingContext context) {
        if (null != withdraw.getBaseUser()) {
            Driver driver = driverService.show(withdraw.getBaseUser().getId());
            DriverRepresentation data = mappingService.map(driver, DriverRepresentation.class, false);
            representation.setUser(data);
        }
    }

}
