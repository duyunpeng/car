package pengyi.domain.service.permission;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.permission.command.CreatePermissionCommand;
import pengyi.application.permission.command.EditPermissionCommand;
import pengyi.application.permission.command.ListPermissionCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.permission.IPermissionRepository;
import pengyi.domain.model.permission.Permission;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("permissionService")
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository<Permission, String> permissionRepository;

    @Override
    public Pagination<Permission> pagination(ListPermissionCommand command) {
        List<Criterion> criteriaList = new ArrayList();
        if (!CoreStringUtils.isEmpty(command.getPermissionName())) {
            criteriaList.add(Restrictions.like("permissionName", command.getPermissionName(), MatchMode.ANYWHERE));
        }

        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));
        }
        return permissionRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, null);
    }

    @Override
    public Permission create(CreatePermissionCommand command) {

        if (null != this.searchByName(command.getPermissionName())) {
            throw new ExistException("权限名[" + command.getPermissionName() + "的记录以存在]");
        }

        Permission permission = new Permission(command.getPermissionName(), command.getDescription(), command.getStatus());
        permissionRepository.save(permission);
        return permission;
    }

    @Override
    public Permission edit(EditPermissionCommand command) {
        Permission permission = this.show(command.getId());

        permission.fainWhenConcurrencyViolation(command.getVersion());

        if (!command.getPermissionName().equals(permission.getPermissionName())) {
            if (null != permissionRepository.getByName(command.getPermissionName())) {
                throw new ExistException("权限名[" + command.getPermissionName() + "的记录以存在]");
            }
        }

        permission.setPermissionName(command.getPermissionName());
        permission.setDescription(command.getDescription());

        permissionRepository.update(permission);

        return permission;
    }

    @Override
    public Permission show(String id) {
        Permission permission = permissionRepository.getById(id);
        if (null == permission) {
            throw new NoFoundException("没有找到权限id=[" + id + "]的记录");
        }
        return permission;
    }

    @Override
    public Permission updateStatus(EditStatusCommand command) {
        Permission permission = this.show(command.getId());
        permission.fainWhenConcurrencyViolation(command.getVersion());

        if (permission.getStatus() == EnableStatus.ENABLE) {
            permission.setStatus(EnableStatus.DISABLE);
        } else {
            permission.setStatus(EnableStatus.ENABLE);
        }

        permissionRepository.update(permission);

        return permission;
    }

    @Override
    public Permission searchByName(String permissionName) {
        return permissionRepository.getByName(permissionName);
    }

    @Override
    public List<Permission> getPermissionsByIds(String[] ids) {
        List<Permission> permissions = null;
        if (null != ids && ids.length > 0) {
            permissions = new ArrayList<Permission>();
            for (String item : ids) {
                Permission permission = this.show(item);
                permissions.add(permission);
            }
        }
        return permissions;
    }
}
