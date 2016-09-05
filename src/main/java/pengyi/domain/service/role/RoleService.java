package pengyi.domain.service.role;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.role.command.CreateRoleCommand;
import pengyi.application.role.command.EditRoleCommand;
import pengyi.application.role.command.ListRoleCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.permission.Permission;
import pengyi.domain.model.role.IRoleRepository;
import pengyi.domain.model.role.Role;
import pengyi.domain.service.permission.IPermissionService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("roleService")
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository<Role, String> roleRepository;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public Pagination<Role> pagination(ListRoleCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getRoleName())) {
            criteriaList.add(Restrictions.like("roleName", command.getRoleName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return roleRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public Role create(CreateRoleCommand command) {
        if (null != this.searchByName(command.getRoleName())) {
            throw new ExistException("角色名[" + command.getRoleName() + "]的记录已存在");
        }

        List<Permission> permissions = permissionService.getPermissionsByIds(command.getPermissions());

        Role role = new Role(command.getRoleName(), command.getDescription(), command.getStatus(), permissions);

        roleRepository.save(role);

        return role;
    }

    @Override
    public Role edit(EditRoleCommand command) {
        Role role = this.show(command.getId());

        role.fainWhenConcurrencyViolation(command.getVersion());

        if (!role.getRoleName().equals(command.getRoleName())) {
            if (null != this.searchByName(command.getRoleName())) {
                throw new ExistException("角色名[" + command.getRoleName() + "]的记录已存在");
            }
        }

        List<Permission> permissions = permissionService.getPermissionsByIds(command.getPermissions());


        role.setRoleName(command.getRoleName());
        role.setDescription(command.getDescription());
        role.setPermissions(permissions);

        roleRepository.update(role);
        return role;
    }

    @Override
    public Role show(String id) {
        Role role = roleRepository.getById(id);
        if (null == role) {
            throw new NoFoundException("没有找到角色id=[" + id + "]的记录");
        }
        return role;
    }

    @Override
    public Role updateStatus(EditStatusCommand command) {
        Role role = this.show(command.getId());
        role.fainWhenConcurrencyViolation(command.getVersion());

        if (role.getStatus() == EnableStatus.ENABLE) {
            role.setStatus(EnableStatus.DISABLE);
        } else {
            role.setStatus(EnableStatus.ENABLE);
        }
        roleRepository.update(role);
        return role;
    }

    @Override
    public Role searchByName(String roleName) {
        return roleRepository.getByName(roleName);
    }

    @Override
    public List<Role> roleList() {
        return roleRepository.findAll();
    }
}
