package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.order.IApiOrderAppService;
import pengyi.application.order.IApiOrderWayPointAppService;
import pengyi.application.order.command.*;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ConcurrencyException;
import pengyi.core.exception.NotSufficientFundsException;
import pengyi.core.exception.OrderIsStartException;
import pengyi.core.type.UserType;

import javax.servlet.http.HttpSession;

/**
 * Created by pengyi on 2016/4/28.
 */
@Controller
@RequestMapping("/api/app/order/way")
public class ApiAppOrderWayPointController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiOrderWayPointAppService apiOrderWayPointAppService;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public BaseResponse waitOrder(UploadWayCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiOrderWayPointAppService.upload(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
