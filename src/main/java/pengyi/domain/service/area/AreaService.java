package pengyi.domain.service.area;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.area.command.CreateAreaCommand;
import pengyi.application.area.command.EditAreaCommand;
import pengyi.application.area.command.ListAreaCommand;
import pengyi.application.area.command.SearchAreaListCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.area.IAreaRepository;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Service("areaService")
public class AreaService implements IAreaService {

    @Autowired
    private IAreaRepository<Area, String> areaRepository;

    @Override
    public Pagination<Area> pagination(ListAreaCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        if (!CoreStringUtils.isEmpty(command.getName())) {
            criterionList.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("priority"));

        return areaRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Area create(CreateAreaCommand command) {
        Area parent = null;
        if (!CoreStringUtils.isEmpty(command.getParent())) {
            parent = this.show(command.getParent());
        }

        Area area = new Area(command.getName(), command.getPriority(), parent);

        areaRepository.save(area);

        return area;
    }

    @Override
    public Area edit(EditAreaCommand command) {
        Area area = this.show(command.getId());
        area.fainWhenConcurrencyViolation(command.getVersion());

        Area parent = null;
        if (!CoreStringUtils.isEmpty(command.getParent())) {
            parent = this.show(command.getParent());
        }

        area.setName(command.getName());
        area.setPriority(command.getPriority());
        area.setParent(parent);

        areaRepository.update(area);
        return area;
    }

    @Override
    public Area show(String id) {
        Area area = areaRepository.getById(id);
        if (null == area) {
            throw new NoFoundException("没有找到区域id=[" + id + "]的记录");
        }
        return area;
    }

    @Override
    public List<Area> searchByParent(String parentId) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(parentId)) {
            criterionList.add(Restrictions.eq("parent.id", parentId));
        } else {
            criterionList.add(Restrictions.isNull("parent"));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("priority"));
        return areaRepository.list(criterionList, orderList);
    }

    @Override
    public List<Area> apiSearchAreaList(SearchAreaListCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getId())) {
            criterionList.add(Restrictions.eq("id", command.getId()));
        }
        if (!CoreStringUtils.isEmpty(command.getName())) {
            criterionList.add(Restrictions.eq("name", command.getName()));
        }
        if (!CoreStringUtils.isEmpty(command.getParent())) {
            criterionList.add(Restrictions.eq("parent.id", command.getParent()));
        } else {
            criterionList.add(Restrictions.isNull("parent"));
        }
        return areaRepository.list(criterionList, null);
    }

}
