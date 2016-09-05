package pengyi.application.user.terrace;

import pengyi.application.user.terrace.command.EditTerraceCommand;
import pengyi.application.user.terrace.command.BaseListTerraceCommand;
import pengyi.application.user.terrace.representation.TerraceRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/7.
 */
public interface ITerraceAppService {

    Pagination<TerraceRepresentation> pagination(BaseListTerraceCommand command);

    TerraceRepresentation edit(EditTerraceCommand command);

    TerraceRepresentation show(String id);
}
