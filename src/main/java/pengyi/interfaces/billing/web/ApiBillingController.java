package pengyi.interfaces.billing.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.billing.IApiBillingAppService;
import pengyi.application.billing.command.CreateBillingCommand;
import pengyi.application.billing.command.EditBillingCommand;
import pengyi.application.billing.command.ListBillingCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

/**
 * Created by YJH on 2016/3/25.
 */
@Controller
@RequestMapping("/billing/api")
public class ApiBillingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiBillingAppService apiBillingAppService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public BaseResponse create(CreateBillingCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiBillingAppService.create(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse list(ListBillingCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiBillingAppService.apiPagination(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public BaseResponse showByCompany(String id) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiBillingAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public BaseResponse edit(EditBillingCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiBillingAppService.edit(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
