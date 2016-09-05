package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.evaluate.IApiEvaluateAppService;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.exception.NoFoundException;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/22.
 */
@Controller
@RequestMapping("/api/app/evaluate")
public class ApiAppEvaluateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiEvaluateAppService apiEvaluateAppService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public BaseResponse createEvaluate(CreateEvaluateCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setEvaluateUser(baseUser.getId());
        BaseResponse response = null;

        try {
            response = apiEvaluateAppService.createEvaluate(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/get_by_orderId")
    @ResponseBody
    public BaseResponse info(String orderId) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiEvaluateAppService.getByOrderId(orderId);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
