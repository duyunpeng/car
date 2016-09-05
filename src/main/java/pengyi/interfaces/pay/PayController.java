package pengyi.interfaces.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.pay.IPayAppService;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.exception.WechatSignException;
import pengyi.core.type.PayType;
import pengyi.core.util.HttpUtil;
import pengyi.core.util.IPUtil;
import pengyi.core.util.Signature;
import pengyi.core.util.XMLParser;
import pengyi.domain.model.pay.AlipayNotify;
import pengyi.domain.model.pay.WechatNotify;
import pengyi.interfaces.shared.web.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Created by pengyi on 2016/3/14.
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPayAppService payAppService;

    @RequestMapping(value = "/wechat/pay")
    @ResponseBody
    public BaseResponse wechatPay(String orderId, HttpServletRequest request) {

        long starttime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = payAppService.wechatPay(orderId, IPUtil.getIpAddress(request));
        } catch (WechatSignException e) {
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, System.currentTimeMillis() - starttime, null, e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/alipay/notify")
    @ResponseBody
    public String alipayNotify(AlipayNotify notify, HttpServletRequest request, Locale locale) {

        Map<String, Object> map = request.getParameterMap();
        for (Map.Entry entry : map.entrySet()) {
            logger.info(entry.getKey() + ">----------->" + ((String[]) entry.getValue())[0]);
        }
        try {
            if ("true".equals(HttpUtil.urlConnection(Constants.ALIPAY_NOTIFY_VERIFY_URL,
                    Constants.ALIPAY_NOTIFY_VERIFY_PARAM + notify.getNotify_id()))) {
                if (notify.getTrade_status().equals("TRADE_SUCCESS")) {
                    payAppService.alipaySuccess(notify);
                    logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.ALIPAY}, locale));
                } else if (notify.getTrade_status().equals("TRADE_FINISHED")) {
                    payAppService.alipaySuccess(notify);
                    logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.ALIPAY}, locale));
                } else if (notify.getTrade_status().equals("WAIT_BUYER_PAY")) {

                }
                return "success";
            } else {
                logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), "不是支付宝通知"}, locale));
            }
        } catch (Exception e) {
            logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), e.getMessage()}, locale));
        }

        return "fail";
    }

    @RequestMapping(value = "/wechat/notify")
    @ResponseBody
    public String wechatNotify(HttpServletRequest request, Locale locale) {

        int contentLength = request.getContentLength();
        byte[] bytes = new byte[contentLength];
        WechatNotify notify = null;
        try {
            request.getInputStream().read(bytes, 0, contentLength);
            notify = (WechatNotify) XMLParser.getObjFromXML(new String(bytes), WechatNotify.class);
            logger.info("response---------------->" + new String(bytes, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sign = null;
        if (notify != null) {
            sign = notify.getSign();
            notify.setSign("");
            try {
                String mySign = Signature.getWechatSign(notify);
                if (mySign.equals(sign)) {
                    if (notify.getReturn_code().equals("SUCCESS")) {
                        if (notify.getResult_code().equals("SUCCESS")) {
                            payAppService.wechatSuccess(notify);
                            logger.info(getMessage("pay.success.message", new Object[]{notify.getOut_trade_no(), PayType.WECHAT}, locale));
                            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                        } else {
                            logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), notify.getErr_code_des()}, locale));
                        }

                    } else {
                        logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), notify.getReturn_msg()}, locale));
                    }
                } else {
                    logger.info(getMessage("pay.fail.message", new Object[]{notify.getOut_trade_no(), "签名验证失败"}, locale));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return "false";
    }

}
