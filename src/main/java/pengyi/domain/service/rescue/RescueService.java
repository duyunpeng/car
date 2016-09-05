package pengyi.domain.service.rescue;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.command.RescueSuccessCommand;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.RescueStatus;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.rescue.IRescueRepository;
import pengyi.domain.model.rescue.Rescue;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.area.AreaService;
import pengyi.domain.service.user.BaseUserService;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.domain.service.user.driver.DriverService;
import pengyi.repository.generic.Pagination;

import java.util.*;


/**
 * Created by lvdi on 2015/3/8.
 */

@Service("rescueService")
public class RescueService implements IRescueService {

    @Autowired
    private IRescueRepository<Rescue, String> rescueRepository;

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ICompanyService companyService;

//    @Override
//    public List<Rescue> findAllRescue() {
//        return rescueRepository.findAll();
//    }

    @Override
    public Rescue getById(String rescueId) {
        return rescueRepository.getById(rescueId);
    }

    @Override
    public Pagination<Rescue> pagination(ListRescueCommand command) {

        List<Criterion> criteriaList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getApplyUser())) {
            aliasMap.put("applyUser", "applyUser");
            criteriaList.add(Restrictions.like("applyUser.userName", command.getApplyUser(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getDriver())) {
            aliasMap.put("driver", "driver");
            criteriaList.add(Restrictions.like("driver.userName", command.getDriver(), MatchMode.ANYWHERE));
        }
        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status", command.getStatus()));

        }
        if (null != command.getRescueType()) {
            criteriaList.add(Restrictions.eq("rescueType", command.getRescueType()));
        }

        if (!CoreStringUtils.isEmpty(command.getCompany())) {
            Company company = companyService.show(command.getCompany());
            command.setArea(company.getOperateAddress().getId());
        }

        if (null != command.getArea()) {
            aliasMap.put("area", "area");
            aliasMap.put("area.parent", "parent");
            criteriaList.add(Restrictions.or(Restrictions.eq("area.id", command.getArea()),
                    Restrictions.eq("parent.id", command.getArea()),
                    Restrictions.eq("parent.parent.id", command.getArea())));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("applyTime"));

        return rescueRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, aliasMap, orderList, null, null);
    }

    @Override
    public Rescue create(CreateRescueCommand command) {

        Area area = areaService.show(command.getArea());

        BaseUser applyUser = baseUserService.show(command.getApplyUser());
        Rescue rescue1 = new Rescue(applyUser, new Date(), command.getRescueType(),
                command.getDescription(), null, null, RescueStatus.WAIT_RESCUE, null, null, command.getRescueAddress(), command.getName(), command.getPhone(), area);
        rescueRepository.save(rescue1);

        return rescue1;
    }

    @Override
    public Rescue edit(EditRescueCommand command) {
        Rescue rescue = this.show(command.getId());
        BaseUser applyUser = baseUserService.show(command.getApplyUser());
        Driver driver = driverService.show(command.getDriver());
        rescue.setApplyUser(applyUser);
        rescue.setStatus(command.getRescueStatus());
//        rescue.setType(command.getType());
        rescue.setDescription(command.getDescription());
        rescue.setDriver(driver);
        rescueRepository.save(rescue);

        return rescue;
    }

    @Override
    public Rescue show(String id) {
        Rescue rescue = rescueRepository.getById(id);
        if (null == rescue) {
            throw new NoFoundException("没有找到救援id=[" + id + "]的记录");
        }
        return rescue;
    }

    @Override
    public Rescue updateStatus(EditRescueCommand command) {
        Rescue rescue = this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        if (rescue.getStatus().equals(RescueStatus.WAIT_RESCUE)) {
            rescue.setStatus(RescueStatus.IN_RESCUE);
        } else if (rescue.getStatus().equals(RescueStatus.IN_RESCUE)) {
            rescue.setStatus(RescueStatus.SUCCESS_RESCUE);
        }
        rescueRepository.update(rescue);
        return rescue;
    }

    @Override
    public Rescue searchByName(String rescueName) {
        return rescueRepository.getByName(rescueName);
    }


    /**
     * 处理救援
     */
    @Override
    public Rescue apiUpdateRescue(EditRescueCommand command) {
        Rescue rescue = this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        Driver driver = driverService.show(command.getDriver());
        rescue.setStatus(command.getRescueStatus());
        rescue.setDriver(driver);
        return rescue;
    }

    /**
     * 取消救援
     */
    @Override
    public Rescue apiCancelRescue(EditRescueCommand command) {

        Rescue rescue = this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        rescue.setStatus(RescueStatus.CANCEL_RESCUE);
        rescueRepository.update(rescue);
        return rescue;
    }

    /**
     * 司机完成
     */
    @Override
    public Rescue apiDriverFinishRescue(RescueSuccessCommand command) {

        Rescue rescue = this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        rescue.setStatus(RescueStatus.WAIT_AUDIT);
        rescueRepository.update(rescue);
        return rescue;
    }

    /**
     * 完成救援
     */
    @Override
    public Rescue apiFinishRescue(RescueSuccessCommand command) {

        Rescue rescue = this.show(command.getId());
        rescue.fainWhenConcurrencyViolation(command.getVersion());
        rescue.setStatus(RescueStatus.SUCCESS_RESCUE);
        rescueRepository.update(rescue);
        return rescue;
    }

    @Override
    public Pagination searchRescue(ListRescueCommand command) {
        List<Criterion> criteriaList = new ArrayList<Criterion>();
        if (null != command.getStatus()) {
            criteriaList.add(Restrictions.eq("status.WAIT_RESCUE", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("applyTime"));
        Map<String, String> aliasMap = new HashMap<String, String>();
        aliasMap.put("applyUser", "a");
        aliasMap.put("driver", "d");

        return rescueRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, aliasMap, orderList, null, null);
    }

    @Override
    public Pagination<Rescue> userAndDriverList(ListRescueCommand command) {
        List<Criterion> criteriaList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getApplyUser())) {
            aliasMap.put("applyUser", "applyUser");
            criteriaList.add(Restrictions.eq("applyUser.id", command.getApplyUser()));
        }
        if (!CoreStringUtils.isEmpty(command.getDriver())) {
            aliasMap.put("driver", "driver");
            criteriaList.add(Restrictions.eq("driver.id", command.getDriver()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("status"));

        return rescueRepository.pagination(command.getPage(), command.getPageSize(), criteriaList, aliasMap, orderList, null, null);
    }


}
