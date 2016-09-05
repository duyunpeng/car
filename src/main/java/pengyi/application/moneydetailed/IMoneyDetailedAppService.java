package pengyi.application.moneydetailed;

import pengyi.application.moneydetailed.command.CreateMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.EditMoneyDetailedCommand;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.application.moneydetailed.representation.MoneyDetailedRepresentation;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IMoneyDetailedAppService {

    Pagination<MoneyDetailedRepresentation> pagination(ListMoneyDetailedCommand command);

    MoneyDetailedRepresentation create(CreateMoneyDetailedCommand command);

    MoneyDetailedRepresentation edit(EditMoneyDetailedCommand command);

    MoneyDetailedRepresentation show(String id);

    List<MoneyDetailedRepresentation> exportExcel(ListMoneyDetailedCommand command);
}
