package pengyi.interfaces.evaluate.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.evaluate.IApiEvaluateAppService;
import pengyi.application.evaluate.command.CreateEvaluateCommand;
import pengyi.application.evaluate.command.EditEvaluateCommand;
import pengyi.application.evaluate.representation.EvaluateRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.exception.ConcurrencyException;


/**
 * Created by LvDi on 2016/3/17.
 */
@Controller
@RequestMapping("/api/evaluate")
public class ApiEvaluateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiEvaluateAppService iApiEvaluateAppService;

    /**
     * (司机和用户)发起评价
     */
    @RequestMapping(value = "/sponsor")
    @ResponseBody
    public BaseResponse create(CreateEvaluateCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            baseResponse = iApiEvaluateAppService.createEvaluate(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, e.getMessage());

        }
        return baseResponse;
    }

    /**
     * (司机和用户)查看评价
     */
    @RequestMapping("/show")
    @ResponseBody
    public BaseResponse show(String order) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            EvaluateRepresentation evaluateRepresentation = iApiEvaluateAppService.show(order);
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, evaluateRepresentation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        return baseResponse;
    }

    /**
     * (司机和用户)修改自己发起的评价
     */
    @RequestMapping("/update")
    @ResponseBody
    public BaseResponse edit(EditEvaluateCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            baseResponse = iApiEvaluateAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 取消评价
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(String order) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            baseResponse = iApiEvaluateAppService.delete(order);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        return baseResponse;
    }

}
