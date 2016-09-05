package pengyi.interfaces.roport.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.report.IApiReportAppService;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.exception.ConcurrencyException;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/16.
 */
@Controller
@RequestMapping("/report/api")
public class ApiReportController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiReportAppService apiReportService;

    /**
     * 处理举报
     */
    @RequestMapping(value = "/deal_report")
    @ResponseBody
    public BaseResponse edit(EditReportCommand command){
        long startTime = System.currentTimeMillis();
        BaseResponse response=null;
        try {
            response = apiReportService.updateReport(command);

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
    @RequestMapping(value = "/finish_report")
    @ResponseBody
    public BaseResponse finishReport(EditReportCommand command){
        long startTime = System.currentTimeMillis();
        BaseResponse response=null;
        try {
            response = apiReportService.finishReport(command);

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

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse searchByCompany(ListReportCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiReportService.list(command);


        } catch (Exception e) {

            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/show_by_reportId")
    @ResponseBody
    public BaseResponse showByReportId(String id) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            ReportRepresentation representation=apiReportService.show(id);
            response= new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS,0,representation,ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());

        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
    @RequestMapping(value="/create")
    @ResponseBody
    public BaseResponse create(CreateReportCommand command){
        long startTime=System.currentTimeMillis();
        BaseResponse response=null;
        try{
            response=apiReportService.create(command);
        }catch (Exception e){
            logger.warn(e.getMessage());
            response=new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR,0,null,ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis()-startTime);
        return response;
    }
}
