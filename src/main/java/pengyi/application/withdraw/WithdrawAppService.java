package pengyi.application.withdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.withdraw.command.CreateWithdrawCommand;
import pengyi.application.withdraw.command.EditWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.application.withdraw.representation.WithdrawExtendRepresentation;
import pengyi.application.withdraw.representation.WithdrawRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.exception.NotSufficientFundsException;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.withdraw.Withdraw;
import pengyi.domain.service.withdraw.IWithdrawService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/5/6.
 */
@Service("withdrawAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class WithdrawAppService implements IWithdrawAppService {

    @Autowired
    private IWithdrawService withdrawService;
    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse apply(CreateWithdrawCommand command) {
        if (null == command.getMoney() || 0 == command.getMoney().intValue()) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10050.getMessage());
        }
        try {
            withdrawService.apply(command);
        } catch (NotSufficientFundsException e) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_SUFFICIENT_FUNDS, 0, null, ResponseCode.RESPONSE_CODE_NOT_SUFFICIENT_FUNDS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse list(ListWithdrawCommand command) {
        Pagination<Withdraw> pagination = withdrawService.list(command);
        List<WithdrawRepresentation> representations = mappingService.mapAsList(pagination.getData(), WithdrawRepresentation.class);
        Pagination<WithdrawRepresentation> representationPagination = new Pagination<WithdrawRepresentation>(representations, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, representationPagination, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<WithdrawRepresentation> pagination(ListWithdrawCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Withdraw> pagination = withdrawService.pagination(command);
        List<WithdrawRepresentation> representations = mappingService.mapAsList(pagination.getData(), WithdrawRepresentation.class);
        return new Pagination<WithdrawRepresentation>(representations, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public void finish(EditWithdrawCommand command) {
        withdrawService.finish(command);
    }

    @Override
    public List<WithdrawExtendRepresentation> exportExcel(ListWithdrawCommand command) {
        return mappingService.mapAsList(withdrawService.exportExcel(command),WithdrawExtendRepresentation.class);
    }
}
