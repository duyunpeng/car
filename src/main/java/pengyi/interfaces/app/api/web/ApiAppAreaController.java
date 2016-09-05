package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.area.IApiAreaAppService;
import pengyi.application.area.command.SearchAreaListCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

/**
 * Created by YJH on 2016/3/22.
 */
@Controller
@RequestMapping("/api/app/area")
public class ApiAppAreaController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiAreaAppService apiAreaAppService;

    @RequestMapping(value = "/search")
    @ResponseBody
    public BaseResponse apiSearchAreaList(SearchAreaListCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiAreaAppService.apiSearchAreaList(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
