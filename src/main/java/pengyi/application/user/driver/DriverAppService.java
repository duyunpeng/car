package pengyi.application.user.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.application.user.driver.command.BaseListDriverCommand;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("driverAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class DriverAppService implements IDriverAppService {

    @Autowired
    private IDriverService driverService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<DriverRepresentation> pagination(BaseListDriverCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Driver> pagination = driverService.pagination(command);
        List<DriverRepresentation> data = mappingService.mapAsList(pagination.getData(), DriverRepresentation.class);
        return new Pagination<DriverRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public DriverRepresentation edit(EditDriverCommand command) {
        return mappingService.map(driverService.edit(command), DriverRepresentation.class, false);
    }

    @Override
    public DriverRepresentation show(String id) {
        return mappingService.map(driverService.show(id), DriverRepresentation.class, false);
    }

    @Override
    public DriverRepresentation updateStatus(EditStatusCommand command) {
        return mappingService.map(driverService.auth(command), DriverRepresentation.class, false);
    }

    @Override
    public DriverRepresentation create(CreateDriverCommand command) {
        return mappingService.map(driverService.terraceCreate(command),DriverRepresentation.class,false);
    }
}
