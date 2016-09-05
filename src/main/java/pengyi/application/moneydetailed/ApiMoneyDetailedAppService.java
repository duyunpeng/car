package pengyi.application.moneydetailed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.application.moneydetailed.representation.MoneyDetailedRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.moneydetailed.MoneyDetailed;
import pengyi.domain.service.moneydetailed.IMoneyDetailedService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("apiMoneyDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiMoneyDetailedAppService implements IApiMoneyDetailedAppService {

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse pagination(ListMoneyDetailedCommand command) {
        if (null != command) {
            command.verifyPage();
            command.verifyPageSize(10);
            Pagination<MoneyDetailed> pagination = moneyDetailedService.pagination(command);
            List<MoneyDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), MoneyDetailedRepresentation.class);

            Pagination<MoneyDetailedRepresentation> result = new Pagination<MoneyDetailedRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse count(ListMoneyDetailedCommand command) {
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, moneyDetailedService.sum(command), ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

}
