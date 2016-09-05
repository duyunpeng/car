package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.IApiBaseUserAppService;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.redis.RedisService;
import pengyi.core.sms.obj.SmsTemplate;
import pengyi.core.sms.service.SmsSender;
import pengyi.core.util.CoreStringUtils;

/**
 * Created by YJH on 2016/3/21.
 */
@Controller
@RequestMapping("/api/app/sms")
public class ApiAppSmsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiBaseUserAppService apiBaseUserAppService;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/send_register")
    @ResponseBody
    public BaseResponse sendRegister(String phone) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            BaseUserRepresentation baseUser = apiBaseUserAppService.apiSearchByUserName(phone);
            if (null == baseUser) {
                if (redisService.exists(phone)) {
                    response = new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_OVERDUE, 0, null,
                            ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_OVERDUE.getMessage());
                } else {
                    String code = CoreStringUtils.randomNum(6);
                    smsSender.send(phone, code, SmsTemplate.REGISTER);
                    redisService.addCache(phone, code);
                    response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
                }
            } else {
                response = new BaseResponse(ResponseCode.RESPONSE_CODE_ACCOUNT_EXIST, 0, null, ResponseCode.RESPONSE_CODE_ACCOUNT_EXIST.getMessage());
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/send_reset_password")
    @ResponseBody
    public BaseResponse sendResetPassword(String phone) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            BaseUserRepresentation baseUser = apiBaseUserAppService.apiSearchByUserName(phone);
            if (null != baseUser) {
                if (redisService.exists(phone)) {
                    response = new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_OVERDUE, 0, null,
                            ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_OVERDUE.getMessage());
                } else {
                    String code = CoreStringUtils.randomNum(6);
                    smsSender.send(phone, code, SmsTemplate.RESETPWD);
                    redisService.addCache(phone, code);
                    response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
                }
            } else {
                response = new BaseResponse(ResponseCode.RESPONSE_CODE_UNKNOWN_ACCOUNT, 0, null, ResponseCode.RESPONSE_CODE_UNKNOWN_ACCOUNT.getMessage());
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/send_rescue")
    @ResponseBody
    public BaseResponse sendRescue(String phone) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            if (redisService.exists(phone)) {
                response = new BaseResponse(ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_OVERDUE, 0, null,
                        ResponseCode.RESPONSE_CODE_VERIFICATION_CODE_NOT_OVERDUE.getMessage());
            } else {
                String code = CoreStringUtils.randomNum(6);
                smsSender.send(phone, code, SmsTemplate.AUTHENTICATION);
                redisService.addCache(phone, code);
                response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
