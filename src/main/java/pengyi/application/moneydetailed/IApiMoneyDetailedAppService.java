package pengyi.application.moneydetailed;

import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.core.api.BaseResponse;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IApiMoneyDetailedAppService {
    BaseResponse pagination(ListMoneyDetailedCommand command);

    BaseResponse count(ListMoneyDetailedCommand command);
}
