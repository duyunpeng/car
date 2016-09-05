package pengyi.domain.service.withdraw;

import pengyi.application.withdraw.command.CreateWithdrawCommand;
import pengyi.application.withdraw.command.EditWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.core.exception.NotSufficientFundsException;
import pengyi.domain.model.withdraw.Withdraw;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/5/6.
 */
public interface IWithdrawService {
    void apply(CreateWithdrawCommand command) throws NotSufficientFundsException;

    Pagination<Withdraw> list(ListWithdrawCommand command);

    Pagination<Withdraw> pagination(ListWithdrawCommand command);

    void finish(EditWithdrawCommand command);

    List<Withdraw> exportExcel(ListWithdrawCommand command);
}
