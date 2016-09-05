package pengyi.domain.service.permission;

import pengyi.core.commons.command.EditStatusCommand;
import pengyi.application.permission.command.CreatePermissionCommand;
import pengyi.application.permission.command.EditPermissionCommand;
import pengyi.application.permission.command.ListPermissionCommand;
import pengyi.domain.model.permission.Permission;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IPermissionService {

    Pagination<Permission> pagination(ListPermissionCommand command);

    Permission create(CreatePermissionCommand command);

    Permission edit(EditPermissionCommand command);

    Permission show(String id);

    Permission updateStatus(EditStatusCommand command);

    Permission searchByName(String permissionName);

    List<Permission> getPermissionsByIds(String[] ids);
}
