package pengyi.interfaces.order.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.order.IApiOrderWayPointAppService;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

/**
 * Created by pengyi on 2016/4/27.
 */
@Controller
@RequestMapping("/order/way/api")
public class ApiOrderWayPointController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiOrderWayPointAppService apiOrderWayPointAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse companyOrderList(String orderId) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiOrderWayPointAppService.list(orderId);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
