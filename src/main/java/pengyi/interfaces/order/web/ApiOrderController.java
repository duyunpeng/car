package pengyi.interfaces.order.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.order.IApiOrderAppService;
import pengyi.application.order.command.CompanyOrderListCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/order/api")
public class ApiOrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiOrderAppService apiOrderAppService;

    @RequestMapping(value = "/export_excel")
    @ResponseBody
    public BaseResponse exportExcel(CompanyOrderListCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiOrderAppService.exportExcel(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/company_pagination")
    @ResponseBody
    public BaseResponse companyOrderList(CompanyOrderListCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiOrderAppService.companyOrderPagination(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public BaseResponse show(String id){
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiOrderAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
