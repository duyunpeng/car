package pengyi.application.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.permission.command.CreatePermissionCommand;
import pengyi.application.permission.command.EditPermissionCommand;
import pengyi.application.permission.command.ListPermissionCommand;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.permission.Permission;
import pengyi.domain.service.permission.IPermissionService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("permissionAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PermissionAppService implements IPermissionAppService {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<PermissionRepresentation> pagination(ListPermissionCommand command) {
        command.verifyPage();
        command.verifyPageSize(10);
        Pagination<Permission> pagination = permissionService.pagination(command);
        List<PermissionRepresentation> data = mappingService.mapAsList(pagination.getData(), PermissionRepresentation.class);
        return new Pagination<PermissionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<PermissionRepresentation> permissionList(ListPermissionCommand command) {
        command.verifyPage();
        Pagination<Permission> pagination = permissionService.pagination(command);
        List<PermissionRepresentation> data = mappingService.mapAsList(pagination.getData(), PermissionRepresentation.class);
        return new Pagination<PermissionRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public PermissionRepresentation create(CreatePermissionCommand command) {
        return mappingService.map(permissionService.create(command), PermissionRepresentation.class, false);
    }

    @Override
    public PermissionRepresentation edit(EditPermissionCommand command) {
        return mappingService.map(permissionService.edit(command), PermissionRepresentation.class, false);
    }

    @Override
    public PermissionRepresentation show(String id) {
        return mappingService.map(permissionService.show(id), PermissionRepresentation.class, false);
    }

    @Override
    public PermissionRepresentation updateStatus(EditStatusCommand command) {
        return mappingService.map(permissionService.updateStatus(command), PermissionRepresentation.class, false);
    }
}
