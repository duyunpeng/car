package pengyi.domain.service.user.company;

import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.command.UpdatePasswordCommand;
import pengyi.application.user.company.command.BaseListCompanyCommand;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.user.company.Company;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ICompanyService {

    Pagination<Company> pagination(BaseListCompanyCommand command);

    Company edit(EditCompanyCommand command);

    Company show(String id);

    Company create(Company company);

    Company terraceCreate(CreateCompanyCommand command);

    Company updateStatus(EditStatusCommand command);

    List<Company> list(BaseListCompanyCommand companyCommand);

    void update(Company company);

    void addLock();

    /********** api 方法    *************/
    Company apiEdit(EditCompanyCommand command);

    void apiUpdateFolder(UpdateFolderCommand command);

    Company apiCreate(CreateCompanyCommand command);

    Company apiUpdatePassword(UpdatePasswordCommand command);

    List<Company> apiByName(String name);

    Company apiResetPassword(ResetPasswordCommand command);
}
