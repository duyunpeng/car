package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.report.IApiReportAppService;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/29.
 */
@Controller
@RequestMapping("/api/app/report")
public class ApiAppReportController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiReportAppService apiReportAppService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public BaseResponse create(CreateReportCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setReportUser(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiReportAppService.create(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse list(ListReportCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setReportUser(baseUser.getId());
        BaseResponse response = null;
        try {
            response = apiReportAppService.apiPagination(command);
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
            response = apiReportAppService.apiShow(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
