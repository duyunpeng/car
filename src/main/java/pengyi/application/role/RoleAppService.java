package pengyi.application.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.role.command.CreateRoleCommand;
import pengyi.application.role.command.EditRoleCommand;
import pengyi.application.role.command.ListRoleCommand;
import pengyi.application.role.representation.RoleRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.role.Role;
import pengyi.domain.service.role.IRoleService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("roleAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RoleAppService implements IRoleAppService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<RoleRepresentation> pagination(ListRoleCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Role> pagination = roleService.pagination(command);
        List<RoleRepresentation> data = mappingService.mapAsList(pagination.getData(), RoleRepresentation.class);
        return new Pagination<RoleRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public List<RoleRepresentation> roleList() {
        return mappingService.mapAsList(roleService.roleList(), RoleRepresentation.class);
    }

    @Override
    public RoleRepresentation create(CreateRoleCommand command) {
        return mappingService.map(roleService.create(command), RoleRepresentation.class, false);
    }

    @Override
    public RoleRepresentation edit(EditRoleCommand command) {
        return mappingService.map(roleService.edit(command), RoleRepresentation.class, false);
    }

    @Override
    public RoleRepresentation show(String id) {
        return mappingService.map(roleService.show(id), RoleRepresentation.class, false);
    }

    @Override
    public RoleRepresentation updateStatus(EditStatusCommand command) {
        return mappingService.map(roleService.updateStatus(command), RoleRepresentation.class, false);
    }
}
