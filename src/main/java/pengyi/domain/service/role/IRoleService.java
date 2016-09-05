package pengyi.domain.service.role;

import pengyi.application.role.command.CreateRoleCommand;
import pengyi.application.role.command.EditRoleCommand;
import pengyi.application.role.command.ListRoleCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.role.Role;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IRoleService {

    Pagination<Role> pagination(ListRoleCommand command);

    Role create(CreateRoleCommand command);

    Role edit(EditRoleCommand command);

    Role show(String id);

    Role updateStatus(EditStatusCommand command);

    Role searchByName(String roleName);

    List<Role> roleList();
}
