package pengyi.interfaces.rescue.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.car.command.EditCarCommand;
import pengyi.application.rescue.IApiRescueAppService;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.command.RescueSuccessCommand;
import pengyi.application.rescue.representation.RescueRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.exception.ConcurrencyException;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by lvdi on 2016/3/15.
 */
@Controller
@RequestMapping("/rescue/api")
public class ApiRescueController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiRescueAppService iApiRescueAppService;

    /**
     * 根据id查看救援
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public BaseResponse show(String id) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = iApiRescueAppService.apiInfo(id);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    /**
     * (公司)查看救援列表
     */

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResponse allList(ListRescueCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            Pagination<RescueRepresentation> representation = iApiRescueAppService.search(command);
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, representation, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }

    /**
     * (公司)处理救援
     */
    @RequestMapping(value = "/deal")
    @ResponseBody
    public BaseResponse deal(EditRescueCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            baseResponse = iApiRescueAppService.updateRescue(command);
            //        TODO  推送给司机
            //......................
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        baseResponse.setDebug_time(System.currentTimeMillis() - startTime);
        return baseResponse;

    }

    /**
     * (司机和用户)发起救援
     */
    @RequestMapping(value = "/sponsor")
    @ResponseBody
    public BaseResponse sponsor(CreateRescueCommand command) {
        long startTime = System.currentTimeMillis();
        BaseResponse baseResponse = null;
        try {
            baseResponse = iApiRescueAppService.createRescue(command);
            //        TODO  推送给公司
            //......................

        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
        } catch (Exception e) {
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        baseResponse.setDebug_time(System.currentTimeMillis() - startTime);
        return baseResponse;
    }

    /**
     *完成救援
     */
    @RequestMapping(value = "/ finish")
    @ResponseBody
    public BaseResponse  finish(RescueSuccessCommand command){
        long startTime=System.currentTimeMillis();
         BaseResponse baseResponse=null;
        try {
            baseResponse=iApiRescueAppService.finishRescue(command);
        }catch (ConcurrencyException e){
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR, 0, null, ResponseCode.RESPONSE_CODE_CONCURRENCY_ERROR.getMessage());
        }catch (Exception e){
            logger.warn(e.getMessage());
            baseResponse = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }
        baseResponse.setDebug_time(System.currentTimeMillis() - startTime);
        return baseResponse;
    }


}
