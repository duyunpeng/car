package pengyi.application.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.area.command.CreateAreaCommand;
import pengyi.application.area.command.EditAreaCommand;
import pengyi.application.area.command.ListAreaCommand;
import pengyi.application.area.representation.AreaRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.area.Area;
import pengyi.domain.service.area.IAreaService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
@Service("areaAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class AreaAppService implements IAreaAppService {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<AreaRepresentation> pagination(ListAreaCommand command) {
        command.verifyPage();
        command.verifyPageSize(12);
        Pagination<Area> pagination = areaService.pagination(command);
        List<AreaRepresentation> data = mappingService.mapAsList(pagination.getData(), AreaRepresentation.class);
        return new Pagination<AreaRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public AreaRepresentation show(String id) {
        return mappingService.map(areaService.show(id), AreaRepresentation.class, false);
    }

    @Override
    public AreaRepresentation create(CreateAreaCommand command) {
        return mappingService.map(areaService.create(command), AreaRepresentation.class, false);
    }

    @Override
    public AreaRepresentation edit(EditAreaCommand command) {
        return mappingService.map(areaService.edit(command), AreaRepresentation.class, false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AreaRepresentation> searchByParent(String parentId) {
        return mappingService.mapAsList(areaService.searchByParent(parentId), AreaRepresentation.class);
    }

}
