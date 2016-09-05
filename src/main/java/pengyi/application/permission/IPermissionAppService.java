package pengyi.application.permission;

import pengyi.application.permission.command.CreatePermissionCommand;
import pengyi.application.permission.command.EditPermissionCommand;
import pengyi.application.permission.command.ListPermissionCommand;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IPermissionAppService {

    Pagination<PermissionRepresentation> pagination(ListPermissionCommand command);

    Pagination<PermissionRepresentation> permissionList(ListPermissionCommand command);

    PermissionRepresentation create(CreatePermissionCommand command);

    PermissionRepresentation edit(EditPermissionCommand command);

    PermissionRepresentation show(String id);

    PermissionRepresentation updateStatus(EditStatusCommand command);

}
