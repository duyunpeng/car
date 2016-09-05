package pengyi.interfaces.withdraw.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.urlresources.representation.UrlResourcesRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.application.withdraw.IWithdrawAppService;
import pengyi.application.withdraw.command.CreateWithdrawCommand;
import pengyi.application.withdraw.command.ListWithdrawCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pengyi on 2016/5/6.
 */
@Controller
@RequestMapping("/api/withdraw")
public class ApiWithdrawController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IWithdrawAppService withdrawAppService;

    @RequestMapping(value = "/apply")
    @ResponseBody
    public BaseResponse apply(CreateWithdrawCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setUserId(baseUser.getId());
        try {
            response = withdrawAppService.apply(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse list(ListWithdrawCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setBaseUser(baseUser.getId());
        try {
            response = withdrawAppService.list(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
