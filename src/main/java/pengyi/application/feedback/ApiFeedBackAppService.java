package pengyi.application.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.feedback.command.CreateFeedBackCommand;
import pengyi.application.feedback.representation.FeedBackRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.feedback.FeedBack;
import pengyi.domain.service.feedback.IFeedBackService;

/**
 * Created by YJH on 2016/3/30.
 */
@Service("apiFeedBackAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiFeedBackAppService implements IApiFeedBackAppService {

    @Autowired
    private IFeedBackService feedBackService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse create(CreateFeedBackCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getQq()) && CoreStringUtils.isEmpty(command.getEmail()) && CoreStringUtils.isEmpty(command.getPhone())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10033.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getContent())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_40001.getMessage());
            }
            FeedBackRepresentation data = mappingService.map(feedBackService.create(command), FeedBackRepresentation.class, false);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }
}
