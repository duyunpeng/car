package pengyi.domain.service.user.driver;

import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.driver.command.*;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IDriverService {

    Pagination<Driver> pagination(BaseListDriverCommand command);

    Driver edit(EditDriverCommand command);

    Driver show(String id);

    Driver create(Driver driver);

    Driver terraceCreate(CreateDriverCommand command);

    void addLock();

    List<Driver> searchByCompany(String company);

    void updateDriverLevel(String driverId, Double level);

    Driver searchByUserName(String userName);

    void updateReportCount(String driverId);

    void update(Driver driver);

    Driver auth(EditStatusCommand command);

    /***********
     * api 方法
     **************/
    Pagination<Driver> apiPagination(CompanyDriverListCommand command);

    Driver apiCompanyEditDriver(CompanyDriverEditCommand command);

    Driver apiCompanyAuditingDriver(CompanyAuditingDriverCommand command);

    Driver apiCompanyEditStatusDriver(EditStatusCommand command);

    Driver apiCompanyCreateDriver(CreateDriverCommand command);

    Driver apiCompanyExpelDriver(EditStatusCommand command);

    Driver apiRegister(RegisterDriverCommand command);

    Driver apiEdit(EditDriverCommand command);

    Driver apiUpdateHeadPic(UpdateHeadPicCommand command);
}
