package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.command.UpdateHeadPicCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.application.user.user.IApiUserAppService;
import pengyi.application.user.user.command.EditUserCommand;
import pengyi.application.user.user.command.RegisterUserCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/21.
 */
@Controller
@RequestMapping("/api/app/user")
public class ApiAppUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiUserAppService apiUserAppService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public BaseResponse register(RegisterUserCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiUserAppService.register(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public BaseResponse show(HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        String userId = baseUser.getId();
        BaseResponse response = null;
        try {
            response = apiUserAppService.show(userId);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public BaseResponse edit(EditUserCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setId(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiUserAppService.edit(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/update_head_pic")
    @ResponseBody
    public BaseResponse updateHeadPic(UpdateHeadPicCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setId(baseUser.getId());
        BaseResponse response;

        try {
            response = apiUserAppService.updateHeadPic(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, ResponseCode.RESPONSE_CODE_FAILURE.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
