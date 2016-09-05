package pengyi.interfaces.app.api.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.IApiBaseUserAppService;
import pengyi.application.user.IBaseUserAppService;
import pengyi.application.user.command.LoginUserCommand;
import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.domain.model.user.BaseUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by YJH on 2016/3/21.
 */
@Controller
@RequestMapping("/api/app")
public class ApiAppAuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiBaseUserAppService apiBaseUserAppService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseResponse login(LoginUserCommand command, HttpServletRequest request, HttpSession session) {
        long statusTime = System.currentTimeMillis();
        BaseResponse response = null;
        BaseUserRepresentation user = null;
        try {
            user = apiBaseUserAppService.login(command);

            logger.info("用户:" + user.getUserName() + "登录成功!时间:" + new Date());

            session.setAttribute(Constants.SESSION_USER, user);
            session.setMaxInactiveInterval(60*60*24*30);

            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, user, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (UnknownAccountException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_UNKNOWN_ACCOUNT, 0, null, ResponseCode.RESPONSE_CODE_UNKNOWN_ACCOUNT.getMessage());
        } catch (IncorrectCredentialsException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_ERROR_ACCOUNT_PASSWORD, 0, null, ResponseCode.RESPONSE_CODE_ERROR_ACCOUNT_PASSWORD.getMessage());
        } catch (LockedAccountException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_LOCK_ACCOUNT, 0, null, ResponseCode.RESPONSE_CODE_LOCK_ACCOUNT.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - statusTime);
        return response;
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public BaseResponse logout(HttpSession session) {
        long startTime = System.currentTimeMillis();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        session.invalidate();
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, (System.currentTimeMillis() - startTime), null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    @RequestMapping(value = "/reset_password")
    @ResponseBody
    public BaseResponse resetPassword(ResetPasswordCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiBaseUserAppService.resetPassword(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
