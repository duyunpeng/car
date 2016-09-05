package pengyi.application.appversion;

import pengyi.application.appversion.command.CreateAppVersionCommand;
import pengyi.application.appversion.command.ListAppVersionCommand;
import pengyi.application.appversion.representation.AppVersionRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/4/13.
 */
public interface IAppVersionAppService {

    Pagination<AppVersionRepresentation> pagination(ListAppVersionCommand command);

    AppVersionRepresentation create(CreateAppVersionCommand command);

    void updateStatus(EditStatusCommand command);

    AppVersionRepresentation searchByID(String id);

}
