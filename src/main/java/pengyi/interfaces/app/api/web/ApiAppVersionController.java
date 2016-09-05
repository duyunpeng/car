package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.appversion.IApiAppVersionAppService;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

/**
 * Created by YJH on 2016/4/13.
 */
@Controller
@RequestMapping("/api/app/version")
public class ApiAppVersionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiAppVersionAppService apiAppVersionAppService;

    @RequestMapping(value = "/check_version")
    @ResponseBody
    public BaseResponse searchNewVersion() {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiAppVersionAppService.searchNewVersion();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
