package pengyi.application.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.billing.command.CreateBillingCommand;
import pengyi.application.billing.command.EditBillingCommand;
import pengyi.application.billing.command.ListBillingCommand;
import pengyi.application.billing.command.SharedCommand;
import pengyi.application.billing.representation.BillingRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.billing.Billing;
import pengyi.domain.service.billing.IBillingService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/21.
 */
@Service("billingAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class BillingAppService implements IBillingAppService {

    @Autowired
    private IBillingService billingService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<BillingRepresentation> pagination(ListBillingCommand command) {
        command.verifyPage();
        command.verifyPageSize(12);
        Pagination<Billing> pagination = billingService.pagination(command);
        List<BillingRepresentation> data = mappingService.mapAsList(pagination.getData(), BillingRepresentation.class);
        return new Pagination<BillingRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }


    @Override
    @Transactional(readOnly = true)
    public Pagination<BillingRepresentation> waitPagination(ListBillingCommand command) {
        command.verifyPage();
        command.verifyPageSize(12);
        Pagination<Billing> pagination = billingService.waitPagination(command);
        List<BillingRepresentation> data = mappingService.mapAsList(pagination.getData(),BillingRepresentation.class);
        return new Pagination<BillingRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public BillingRepresentation show(String id) {
        return mappingService.map(billingService.show(id), BillingRepresentation.class, false);
    }

    @Override
    public BillingRepresentation create(CreateBillingCommand command) {
        return mappingService.map(billingService.create(command), BillingRepresentation.class, false);
    }

    @Override
    public BillingRepresentation edit(EditBillingCommand command) {
        return mappingService.map(billingService.edit(command), BillingRepresentation.class, false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        billingService.updateStatus(command);
    }

    @Override
    public void waitUpdateStatus(SharedCommand command) {
        billingService.waitUpdateStatus(command);
    }
}
