package pengyi.domain.service.billing;

import pengyi.application.billing.command.*;
import pengyi.domain.model.billing.Billing;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/21.
 */
public interface IBillingService {
    Pagination<Billing> pagination(ListBillingCommand command);

    Pagination<Billing> waitPagination(ListBillingCommand command);

    Billing show(String id);

    Billing create(CreateBillingCommand command);

    Billing edit(EditBillingCommand command);

    Billing searchByCompany(String id);

    Billing searchByID(String id);

    void updateStatus(SharedCommand command);

    void waitUpdateStatus(SharedCommand command);

    List<Billing> searchByDriver(SearchBillingCommand command);

    Pagination<Billing> apiPagination(ListBillingCommand command);
}
