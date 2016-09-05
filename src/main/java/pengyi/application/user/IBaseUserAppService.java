package pengyi.application.user;

import pengyi.application.user.command.*;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IBaseUserAppService {
    BaseUser searchByUserName(String userName);

    Pagination<BaseUserRepresentation> pagination(BaseListBaseUserCommand command);

    BaseUserRepresentation resetPassword(ResetPasswordCommand command);

    BaseUserRepresentation updateStatus(EditStatusCommand command);

    BaseUserRepresentation updateBaseUserRole(EditBaseUserRoleCommand command);

    BaseUserRepresentation create(BaseCreateBaseUserCommand command);

    BaseUserRepresentation show(String id);

    BaseUser login(LoginUserCommand command);
}
