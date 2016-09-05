package pengyi.application.user.user;

import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.command.RegisterUserCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/21.
 */
public interface IApiUserAppService {
    BaseResponse show(String id);

    BaseResponse edit(EditUserCommand command);

    BaseResponse updateHeadPic(UpdateHeadPicCommand command);

    BaseResponse register(RegisterUserCommand command);
}
