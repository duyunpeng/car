package pengyi.application.user.driver;

import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.application.user.driver.command.EditDriverCommand;
import pengyi.application.user.driver.command.BaseListDriverCommand;
import pengyi.application.user.driver.representation.DriverRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IDriverAppService {

    Pagination<DriverRepresentation> pagination(BaseListDriverCommand command);

    DriverRepresentation edit(EditDriverCommand command);

    DriverRepresentation show(String id);

    DriverRepresentation updateStatus(EditStatusCommand command);

    DriverRepresentation create(CreateDriverCommand command);
}
