package pengyi.application.withhold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.withhold.command.CreateWithholdCommand;
import pengyi.application.withhold.command.ListWithholdCommand;
import pengyi.application.withhold.representation.WithholdRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.withhold.Withhold;
import pengyi.domain.service.withhold.IWithHoldService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by pengyi on 2016/5/6.
 */
@Service("withholdAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class WithholdAppService implements IWithholdAppService {

    @Autowired
    private IWithHoldService withHoldService;
    @Autowired
    private IMappingService mappingService;

    @Override
    public void create(CreateWithholdCommand command) {
        withHoldService.create(command);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<WithholdRepresentation> pagination(ListWithholdCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Withhold> withholdPagination = withHoldService.pagination(command);
        List<WithholdRepresentation> representations = mappingService.mapAsList(withholdPagination.getData(), WithholdRepresentation.class);
        return new Pagination<WithholdRepresentation>(representations, withholdPagination.getCount(), command.getPage(), command.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public WithholdRepresentation show(String id) {
        return mappingService.map(withHoldService.show(id), WithholdRepresentation.class, false);
    }

    @Override
    public BaseResponse apiCreate(CreateWithholdCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserId())) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10051.getMessage());
        }
        if (null == command.getMoney()) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10050.getMessage());
        }
        if (CoreStringUtils.isEmpty(command.getDetail())) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_50005.getMessage());
        }
        withHoldService.create(command);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @Override
    public BaseResponse apiList(ListWithholdCommand command) {
        command.verifyPage();
        command.verifyPageSize(20);
        Pagination<Withhold> withholdPagination = withHoldService.pagination(command);
        List<WithholdRepresentation> representations = mappingService.mapAsList(withholdPagination.getData(), WithholdRepresentation.class);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, representations, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }
}
