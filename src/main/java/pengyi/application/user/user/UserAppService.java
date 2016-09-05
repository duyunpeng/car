package pengyi.application.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.command.BaseListUserCommand;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.user.User;
import pengyi.domain.service.user.user.IUserService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("userAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserAppService implements IUserAppService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<UserRepresentation> pagination(BaseListUserCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<User> pagination = userService.pagination(command);
        List<UserRepresentation> data = mappingService.mapAsList(pagination.getData(), UserRepresentation.class);
        return new Pagination<UserRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public UserRepresentation edit(EditUserCommand command) {
        return mappingService.map(userService.edit(command), UserRepresentation.class, false);
    }

    @Override
    public UserRepresentation show(String id) {
        return mappingService.map(userService.show(id), UserRepresentation.class, false);
    }
}
