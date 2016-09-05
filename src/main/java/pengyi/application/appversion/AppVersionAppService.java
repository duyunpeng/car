package pengyi.application.appversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.appversion.command.CreateAppVersionCommand;
import pengyi.application.appversion.command.ListAppVersionCommand;
import pengyi.application.appversion.representation.AppVersionRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.appversion.AppVersion;
import pengyi.domain.service.appversion.IAppVersionService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/4/13.
 */
@Service("appVersionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AppVersionAppService implements IAppVersionAppService {

    @Autowired
    private IAppVersionService appVersionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<AppVersionRepresentation> pagination(ListAppVersionCommand command) {
        command.verifyPage();
        command.verifyPageSize(15);
        Pagination<AppVersion> pagination = appVersionService.pagination(command);
        List<AppVersionRepresentation> data = mappingService.mapAsList(pagination.getData(), AppVersionRepresentation.class);
        return new Pagination<AppVersionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public AppVersionRepresentation create(CreateAppVersionCommand command) {
        return mappingService.map(appVersionService.create(command), AppVersionRepresentation.class, false);
    }

    @Override
    public void updateStatus(EditStatusCommand command) {
        appVersionService.updateStatus(command);
    }

    @Override
    public AppVersionRepresentation searchByID(String id) {
        return mappingService.map(appVersionService.searchByID(id), AppVersionRepresentation.class, false);
    }
}
