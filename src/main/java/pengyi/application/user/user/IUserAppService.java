package pengyi.application.user.user;

import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.command.BaseListUserCommand;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IUserAppService {

    Pagination<UserRepresentation> pagination(BaseListUserCommand command);

    UserRepresentation edit(EditUserCommand command);

    UserRepresentation show(String id);

}
