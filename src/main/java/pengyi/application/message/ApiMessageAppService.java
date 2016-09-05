package pengyi.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.message.command.CompanyCreateMessageCommand;
import pengyi.application.message.command.CompanyListMessageCommand;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.message.Message;
import pengyi.domain.service.message.IMessageService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/15.
 */
@Service("apiMessageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiMessageAppService implements IApiMessageAppService {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public BaseResponse show(String messageId) {
        if (null != messageId) {
            Message message = messageService.show(messageId);
            if (null != message) {
                MessageRepresentation representation = mappingService.map(message, MessageRepresentation.class, false);
                return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, representation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
            }
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_50004.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }

    @Override
    public MessageRepresentation deleteByCompany(String messageId) {

        Message message = messageService.delete(messageId);

        if (null != message) {
            return mappingService.map(message, MessageRepresentation.class, false);
        }

        return null;
    }


    @Override//3
    public BaseResponse apiCreateMessage(CompanyCreateMessageCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getCompany())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
            if (null == command.getReceiveBaseUser()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_50001.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getContent())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_50002.getMessage());
            }
            messageService.apiCreate(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
    }


    @Override//2
    @Transactional(readOnly = true)
    public BaseResponse companyMessageList(CompanyListMessageCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getCompany())) {
                Pagination<Message> pagination = messageService.apiPagination(command);
                List<MessageRepresentation> data = mappingService.mapAsList(pagination.getData(), MessageRepresentation.class);
                Pagination<MessageRepresentation> result = new Pagination<MessageRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
                return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
            } else {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10006.getMessage());
            }
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());

    }

    @Override
    public BaseResponse list(ListMessageCommand command) {
        if (null != command) {
            if (!CoreStringUtils.isEmpty(command.getReceiveBaseUser())) {
                Pagination<Message> pagination = messageService.apiAppList(command);
                List<MessageRepresentation> data = mappingService.mapAsList(pagination.getData(), MessageRepresentation.class);
                Pagination<MessageRepresentation> result = new Pagination<MessageRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
                return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
            } else {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_50001.getMessage());
            }
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }

    @Override
    public BaseResponse apiUnread(String id) {
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, messageService.apiUnread(id), ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

}
