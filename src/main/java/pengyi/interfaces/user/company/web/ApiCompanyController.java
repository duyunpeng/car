package pengyi.interfaces.user.company.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.user.command.ResetPasswordCommand;
import pengyi.application.user.command.UpdatePasswordCommand;
import pengyi.application.user.company.IApiCompanyAppService;
import pengyi.application.user.company.command.CreateCompanyCommand;
import pengyi.application.user.company.command.EditCompanyCommand;
import pengyi.application.user.company.command.UpdateFolderCommand;
import pengyi.application.user.company.representation.CompanyRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.exception.ConcurrencyException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by YJH on 2016/3/15.
 */
@Controller
@RequestMapping("/company/api")
public class ApiCompanyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiCompanyAppService apiCompanyAppService;

    @RequestMapping(value = "/info")
    @ResponseBody
    public BaseResponse show(String id) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiCompanyAppService.apiInfo(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public BaseResponse edit(EditCompanyCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiCompanyAppService.apiEdit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/update_folder")
    @ResponseBody
    public BaseResponse updateFolder(UpdateFolderCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiCompanyAppService.apiUpdateFolder(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public BaseResponse register(CreateCompanyCommand command, HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiCompanyAppService.apiCreateCompany(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/update_password")
    @ResponseBody
    public BaseResponse updatePassword(UpdatePasswordCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiCompanyAppService.apiUpdatePassword(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/reset_password")
    @ResponseBody
    public BaseResponse resetPassword(ResetPasswordCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiCompanyAppService.apiResetPassword(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

}
