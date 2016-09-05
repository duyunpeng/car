package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.moneydetailed.IApiMoneyDetailedAppService;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/30.
 */
@Controller
@RequestMapping("/api/app/money_detailed")
public class ApiAppMoneyDetailedController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiMoneyDetailedAppService apiMoneyDetailedAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse list(ListMoneyDetailedCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setUserId(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiMoneyDetailedAppService.pagination(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public BaseResponse count(ListMoneyDetailedCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setUserId(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiMoneyDetailedAppService.count(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
