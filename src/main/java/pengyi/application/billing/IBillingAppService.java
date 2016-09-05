package pengyi.application.billing;

import pengyi.application.billing.command.CreateBillingCommand;
import pengyi.application.billing.command.EditBillingCommand;
import pengyi.application.billing.command.ListBillingCommand;
import pengyi.application.billing.command.SharedCommand;
import pengyi.application.billing.representation.BillingRepresentation;
import pengyi.repository.generic.Pagination;

/**
 * Created by YJH on 2016/3/21.
 */
public interface IBillingAppService {
    Pagination<BillingRepresentation> pagination(ListBillingCommand command);

    Pagination<BillingRepresentation> waitPagination(ListBillingCommand command);

    BillingRepresentation show(String id);

    BillingRepresentation create(CreateBillingCommand command);

    BillingRepresentation edit(EditBillingCommand command);

    void updateStatus(SharedCommand command);

    void waitUpdateStatus(SharedCommand command);

}
