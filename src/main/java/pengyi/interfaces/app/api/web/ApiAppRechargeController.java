package pengyi.interfaces.app.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.recharge.IRechargeAppService;
import pengyi.application.recharge.command.CreateRechargeCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.exception.WechatSignException;
import pengyi.core.type.PayType;
import pengyi.core.util.IPUtil;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by pengyi on 2016/4/11.
 */
@Controller
@RequestMapping("/recharge")
public class ApiAppRechargeController extends BaseController {

    @Autowired
    private IRechargeAppService rechargeAppService;

    @RequestMapping(value = "/wechat/pay")
    @ResponseBody
    public BaseResponse wechatPay(CreateRechargeCommand command, HttpServletRequest request, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setUserName(baseUser.getUserName());
        command.setPayType(PayType.WECHAT);
        command.setIp(IPUtil.getIpAddress(request));
        BaseResponse response = null;
        try {
            response = rechargeAppService.wechatPay(command);
        } catch (WechatSignException e) {
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, System.currentTimeMillis() - startTime, null, e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/alipay/pay")
    @ResponseBody
    public BaseResponse alipayPay(CreateRechargeCommand command, HttpServletRequest request, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setUserName(baseUser.getUserName());
        command.setPayType(PayType.ALIPAY);
        BaseResponse response = null;
        try {
            response = rechargeAppService.alipayPay(command);
        } catch (WechatSignException e) {
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, System.currentTimeMillis() - startTime, null, e.getMessage());
        }
        return response;
    }

}
