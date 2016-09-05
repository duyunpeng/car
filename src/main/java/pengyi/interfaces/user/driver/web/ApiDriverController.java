package pengyi.interfaces.user.driver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.driver.IApiDriverAppService;
import pengyi.application.user.driver.command.CompanyAuditingDriverCommand;
import pengyi.application.user.driver.command.CompanyDriverEditCommand;
import pengyi.application.user.driver.command.CompanyDriverListCommand;
import pengyi.application.user.driver.command.CreateDriverCommand;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.command.EditStatusCommand;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/driver/api")
public class ApiDriverController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiDriverAppService apiDriverAppService;

    @RequestMapping(value = "/company_driver/list")
    @ResponseBody
    public BaseResponse companyDriverList(CompanyDriverListCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiDriverAppService.companyDriverList(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public BaseResponse show(String id) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiDriverAppService.show(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/company_driver/edit")
    @ResponseBody
    public BaseResponse companyEditDriver(CompanyDriverEditCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiDriverAppService.companyEditDriver(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/company_driver/auditing")
    @ResponseBody
    public BaseResponse companyAuditingDriver(CompanyAuditingDriverCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiDriverAppService.companyAuditingDriver(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/company_driver/update_status")
    @ResponseBody
    public BaseResponse companyEditStatusDriver(EditStatusCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiDriverAppService.companyEditStatusDriver(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/company_driver/create")
    @ResponseBody
    public BaseResponse companyCreateDriver(CreateDriverCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiDriverAppService.companyCreateDriver(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/company_driver/expel")
    @ResponseBody
    public BaseResponse companyExpelDriver(EditStatusCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;

        try {
            response = apiDriverAppService.companyExpelDriver(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
