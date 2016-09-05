package pengyi.domain.service.appversion;

import pengyi.application.appversion.command.CreateAppVersionCommand;
import pengyi.application.appversion.command.ListAppVersionCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.domain.model.appversion.AppVersion;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/4/13.
 */
public interface IAppVersionService {
    Pagination<AppVersion> pagination(ListAppVersionCommand command);

    AppVersion create(CreateAppVersionCommand command);

    void updateStatus(EditStatusCommand command);

    AppVersion searchByID(String id);

    AppVersion searchNewVersion();
}
