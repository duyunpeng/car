package pengyi.application.user.driver;

import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.driver.command.*;
import pengyi.core.api.BaseResponse;
import pengyi.core.commons.command.EditStatusCommand;

/**
 * Created by YJH on 2016/3/15.
 */
public interface IApiDriverAppService {
    BaseResponse companyDriverList(CompanyDriverListCommand command);

    BaseResponse companyEditDriver(CompanyDriverEditCommand command);

    BaseResponse companyAuditingDriver(CompanyAuditingDriverCommand command);

    BaseResponse companyEditStatusDriver(EditStatusCommand command);

    BaseResponse companyCreateDriver(CreateDriverCommand command);

    BaseResponse companyExpelDriver(EditStatusCommand command);

    BaseResponse register(RegisterDriverCommand command);

    BaseResponse show(String userId);

    BaseResponse edit(EditDriverCommand command);

    BaseResponse updateHeadPic(UpdateHeadPicCommand command);
}
