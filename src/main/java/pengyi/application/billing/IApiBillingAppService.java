package pengyi.application.billing;

import pengyi.application.billing.command.*;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/22.
 */
public interface IApiBillingAppService {
    BaseResponse showByCompany(String id);

    BaseResponse create(CreateBillingCommand command);

    BaseResponse edit(EditBillingCommand command);

    BaseResponse searchByDriver(SearchBillingCommand command);

    BaseResponse apiPagination(ListBillingCommand command);

    BaseResponse show(String id);

    void updateStatus(SharedCommand command);
}
