package pengyi.interfaces.message.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.message.IApiMessageAppService;
import pengyi.application.message.command.CompanyCreateMessageCommand;
import pengyi.application.message.command.CompanyListMessageCommand;
import pengyi.application.message.representation.MessageRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;
import pengyi.repository.generic.Pagination;

import javax.servlet.http.HttpSession;


/**
 * Created by liubowen on 2016/3/15.
 */
@Controller
@RequestMapping("/message/api")
public class ApiMessageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiMessageAppService apiMessageService;

    @RequestMapping(value = "/search_by_company")
    @ResponseBody
    public BaseResponse listMessageByCompany(CompanyListMessageCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiMessageService.companyMessageList(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public BaseResponse deleteByCompany(String messageId) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            MessageRepresentation representation = apiMessageService.deleteByCompany(messageId);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, representation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());

        }
        response.setDebug_time((System.currentTimeMillis() - startTime));
        return response;
    }

    @RequestMapping(value = "/create_by_base_user")
    @ResponseBody
    public BaseResponse createMessage(CompanyCreateMessageCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiMessageService.apiCreateMessage(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show_by_messageId")
    @ResponseBody
    public BaseResponse showByMessageId(String id) {

        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiMessageService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
