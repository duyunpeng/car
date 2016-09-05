package pengyi.interfaces.user.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.IApiBaseUserAppService;
import pengyi.application.user.command.BaseListBaseUserCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/base_user/api")
public class ApiBaseUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiBaseUserAppService apiBaseUserAppService;

    @RequestMapping(value = "/search_by_user_name")
    @ResponseBody
    public BaseResponse searchByUserName(String userName) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            BaseUserRepresentation baseUser = apiBaseUserAppService.apiSearchByUserName(userName);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, baseUser, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/search_by_user_name_and_role")
    @ResponseBody
    public BaseResponse searchByUserNameAndRole(BaseListBaseUserCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiBaseUserAppService.apiSearchByUserNameAndRole(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
