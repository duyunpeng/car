package pengyi.domain.service.user.terrace;

import pengyi.application.user.terrace.command.EditTerraceCommand;
import pengyi.application.user.terrace.command.BaseListTerraceCommand;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.terrace.Terrace;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ITerraceService {

    Pagination<Terrace> pagination(BaseListTerraceCommand command);

    Terrace edit(EditTerraceCommand command);

    Terrace show(String id);

    Terrace create(Terrace terrace);
}
