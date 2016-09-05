package pengyi.interfaces.withhold.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.application.withhold.IWithholdAppService;
import pengyi.application.withhold.command.CreateWithholdCommand;
import pengyi.application.withhold.command.ListWithholdCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;

import javax.servlet.http.HttpSession;

/**
 * Created by pengyi on 2016/5/6.
 */
@Controller
@RequestMapping("/api/withhold")
public class ApiWithholdController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWithholdAppService withholdAppService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public BaseResponse apply(CreateWithholdCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setLoginUser(baseUser.getId());
        try {
            response = withholdAppService.apiCreate(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse list(ListWithholdCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setLoginUser(baseUser.getId());
        try {
            response = withholdAppService.apiList(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
