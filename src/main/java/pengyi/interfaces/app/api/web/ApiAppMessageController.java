package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.message.IApiMessageAppService;
import pengyi.application.message.command.ListMessageCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;

import javax.servlet.http.HttpSession;

/**
 * Created by pengyi on 2016/5/5.
 */
@Controller
@RequestMapping("/api/app/message")
public class ApiAppMessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiMessageAppService apiMessageAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse apiSearchAreaList(ListMessageCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        BaseUserRepresentation userRepresentation = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == userRepresentation) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setReceiveBaseUser(userRepresentation.getId());
        try {
            response = apiMessageAppService.list(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    public BaseResponse apiDetail(String messageId) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiMessageAppService.show(messageId);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/unread")
    @ResponseBody
    public BaseResponse apiUnread(HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        BaseUserRepresentation userRepresentation = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == userRepresentation) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        try {
            response = apiMessageAppService.apiUnread(userRepresentation.getId());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
