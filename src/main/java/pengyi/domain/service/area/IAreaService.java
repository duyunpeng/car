package pengyi.domain.service.area;

import pengyi.application.area.command.CreateAreaCommand;
import pengyi.application.area.command.EditAreaCommand;
import pengyi.application.area.command.ListAreaCommand;
import pengyi.application.area.command.SearchAreaListCommand;
import pengyi.domain.model.area.Area;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IAreaService {

    Pagination<Area> pagination(ListAreaCommand command);

    Area create(CreateAreaCommand command);

    Area edit(EditAreaCommand command);

    Area show(String id);

    List<Area> searchByParent(String parentId);

    /***********Api 方法 **************/
    List<Area> apiSearchAreaList(SearchAreaListCommand command);

}
