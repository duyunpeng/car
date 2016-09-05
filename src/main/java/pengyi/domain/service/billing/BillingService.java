package pengyi.domain.service.billing;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.billing.command.*;
import pengyi.core.exception.ExistException;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.DriverType;
import pengyi.core.type.EnableStatus;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.model.billing.IBillingRepository;
import pengyi.domain.model.user.company.Company;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.area.IAreaService;
import pengyi.domain.service.user.company.ICompanyService;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/3/21.
 */
@Service("billingService")
public class BillingService implements IBillingService {

    @Autowired
    private IBillingRepository<Billing, String> billingRepository;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IDriverService driverService;

    @Override
    public Pagination<Billing> pagination(ListBillingCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("status", EnableStatus.ENABLE));
        List<Order> orderList = new ArrayList<Order>();
        return billingRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Pagination<Billing> waitPagination(ListBillingCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("status", EnableStatus.DISABLE));
        List<Order> orderList = new ArrayList<Order>();
        return billingRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public Billing show(String id) {
        Billing billing = billingRepository.getById(id);
        if (null == billing) {
            throw new NoFoundException("没有找到计费模板id=[" + id + "]的记录");
        }
        return billing;
    }

    @Override
    public Billing create(CreateBillingCommand command) {
        Company company = companyService.show(command.getCompany());

        Billing unique = billingRepository.searchUnique(command.getDriverType(), command.getCarType(), company.getId());
        if (null != unique) {
            throw new ExistException("该类型数据已存在，无需重复添加");
        }

        Billing billing = new Billing(command.getKmBilling(), command.getMinuteBilling(), command.getStartingPrice(), company, command.getDriverType(), null, command.getStartKm(), command.getStartMin(),EnableStatus.DISABLE);

        if (null != command.getCarType()) {
            billing.setCarType(command.getCarType());
        }

        billingRepository.save(billing);

        return billing;
    }

    @Override
    public Billing edit(EditBillingCommand command) {
        Billing billing = this.show(command.getId());
        billing.fainWhenConcurrencyViolation(command.getVersion());

//        if (billing.getDriverType() != command.getDriverType() && billing.getCarType() != command.getCarType()) {
//            Billing unique = billingRepository.searchUnique(command.getDriverType(), command.getCarType(), billing.getCompany().getId());
//            if (null != unique) {
//                throw new ExistException("该类型数据已存在，无需重复添加");
//            }
//        }

        billing.setKmBilling(command.getKmBilling());
        billing.setMinuteBilling(command.getMinuteBilling());
        billing.setStartingPrice(command.getStartingPrice());
        billing.setStartKm(command.getStartKm());
        billing.setStartMin(command.getStartMin());
        billing.setStatus(EnableStatus.DISABLE);
//        billing.setDriverType(command.getDriverType());
//        if (null != command.getCarType()) {
//            billing.setCarType(command.getCarType());
//        }
        billingRepository.update(billing);
        return billing;
    }


    @Override
    public Billing searchByID(String id) {
        Billing billing = billingRepository.getById(id);
        if (null == billing) {
            throw new NoFoundException("没有找到ID[" + id + "]的Billing数据");
        }
        return billing;
    }

    @Override
    public Billing searchByCompany(String id) {
        return billingRepository.searchByCompany(id);
    }

    @Override
    public void updateStatus(SharedCommand command) {

        Billing billing = this.searchByID(command.getId());
        billing.fainWhenConcurrencyViolation(command.getVersion());
        if (billing.getStatus() == EnableStatus.DISABLE) {
            billing.setStatus(EnableStatus.ENABLE);
        } else {
            billing.setStatus(EnableStatus.DISABLE);
        }
        billingRepository.update(billing);
    }

    @Override
    public void waitUpdateStatus(SharedCommand command) {
        Billing billing = this.searchByID(command.getId());
        billing.fainWhenConcurrencyViolation(command.getVersion());
        if (billing.getStatus() == EnableStatus.DISABLE) {
            billing.setStatus(EnableStatus.ENABLE);
        } else {
            billing.setStatus(EnableStatus.DISABLE);
        }
        billingRepository.update(billing);
    }

    @Override
    public List<Billing> searchByDriver(SearchBillingCommand command) {
        Driver driver = driverService.searchByUserName(command.getUserName());
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("company.id", driver.getCompany().getId()));
        criterionList.add(Restrictions.eq("driverType", command.getDriverType()));
        if (command.getDriverType() == DriverType.LIMOUSINE) {
            criterionList.add(Restrictions.eq("carType", command.getCarType()));
        }
        return billingRepository.list(criterionList, null);
    }

    @Override
    public Pagination<Billing> apiPagination(ListBillingCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("company.id", command.getCompany()));

        return billingRepository.pagination(command.getPage(), command.getPageSize(), criterionList, null);
    }

}
