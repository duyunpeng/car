package pengyi.application.area;

import pengyi.application.area.command.CreateAreaCommand;
import pengyi.application.area.command.EditAreaCommand;
import pengyi.application.area.command.ListAreaCommand;
import pengyi.application.area.representation.AreaRepresentation;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IAreaAppService {
    Pagination<AreaRepresentation> pagination(ListAreaCommand command);

    AreaRepresentation show(String id);

    AreaRepresentation create(CreateAreaCommand command);

    AreaRepresentation edit(EditAreaCommand command);

    List<AreaRepresentation> searchByParent(String parentId);
}
