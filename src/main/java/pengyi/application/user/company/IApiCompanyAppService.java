package pengyi.application.user.company;

import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.command.UpdatePasswordCommand;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiCompanyAppService {
    BaseResponse apiInfo(String id);

    BaseResponse apiEdit(EditCompanyCommand command);

    BaseResponse apiUpdateFolder(UpdateFolderCommand command);

    BaseResponse apiCreateCompany(CreateCompanyCommand command);

    BaseResponse apiUpdatePassword(UpdatePasswordCommand command);

    BaseResponse apiResetPassword(ResetPasswordCommand command);

    BaseResponse apiList();
}
