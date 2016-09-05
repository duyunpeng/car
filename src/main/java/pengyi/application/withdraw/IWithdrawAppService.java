package pengyi.application.withdraw;

import pengyi.application.withdraw.command.CreateWithdrawCommand;
import pengyi.application.withdraw.command.EditWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.application.withdraw.representation.WithdrawExtendRepresentation;
import pengyi.application.withdraw.representation.WithdrawRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/5/6.
 */
public interface IWithdrawAppService {
    BaseResponse apply(CreateWithdrawCommand command);

    BaseResponse list(ListWithdrawCommand command);

    Pagination<WithdrawRepresentation> pagination(ListWithdrawCommand command);

    void finish(EditWithdrawCommand command);

    List<WithdrawExtendRepresentation> exportExcel(ListWithdrawCommand command);
}
