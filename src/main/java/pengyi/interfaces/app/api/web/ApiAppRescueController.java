package pengyi.interfaces.app.api.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pengyi.application.rescue.IApiRescueAppService;
import pengyi.application.rescue.command.CreateRescueCommand;
import pengyi.application.rescue.command.EditRescueCommand;
import pengyi.application.rescue.command.ListRescueCommand;
import pengyi.application.rescue.command.RescueSuccessCommand;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.commons.Constants;
import pengyi.core.exception.ConcurrencyException;
import pengyi.domain.model.user.BaseUser;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH on 2016/3/23.
 */
@Controller
@RequestMapping("/api/app/rescue")
public class ApiAppRescueController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiRescueAppService apiRescueAppService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public BaseResponse create(CreateRescueCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseUserRepresentation baseUser = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == baseUser) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setApplyUser(baseUser.getId());
        command.setUserName(baseUser.getUserName());
        BaseResponse response = null;

        try {
            response = apiRescueAppService.createRescue(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }


    /**
     *(司机用户)取消救援
     */
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public BaseResponse cancel(EditRescueCommand command){
        long startTime=System.currentTimeMillis();
        BaseResponse baseResponse=null;
        try {
            baseResponse=apiRescueAppService.cancelRescue(command);

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

    /**
     *(司机)救援列表
     */
    @RequestMapping(value = "/driver/list")
    @ResponseBody
    public BaseResponse driverList(ListRescueCommand command, HttpSession session){
        long startTime=System.currentTimeMillis();
        BaseResponse baseResponse=null;
        BaseUserRepresentation userRepresentation = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == userRepresentation) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setDriver(userRepresentation.getId());
        try {
            baseResponse=apiRescueAppService.userAndDriverList(command);

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

    /**
     *(用户)救援列表
     */
    @RequestMapping(value = "/user/list")
    @ResponseBody
    public BaseResponse userList(ListRescueCommand command, HttpSession session){
        long startTime=System.currentTimeMillis();
        BaseResponse baseResponse=null;
        BaseUserRepresentation userRepresentation = (BaseUserRepresentation) session.getAttribute(Constants.SESSION_USER);
        if (null == userRepresentation) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_NOT_LOGIN,
                    System.currentTimeMillis() - startTime, null, ResponseCode.RESPONSE_CODE_NOT_LOGIN.getMessage());
        }
        command.setApplyUser(userRepresentation.getId());
        try {
            baseResponse=apiRescueAppService.userAndDriverList(command);

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

    @RequestMapping(value = "/finish")
    @ResponseBody
    public BaseResponse finish(RescueSuccessCommand command, HttpSession session) {
        long startTime = System.currentTimeMillis();
        BaseResponse response = null;
        try {
            response = apiRescueAppService.driverSuccessRescue(command);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            response = new BaseResponse(ResponseCode.RESPONSE_CODE_FAILURE, 0, null, e.getMessage());
        }

        response.setDebug_time(System.currentTimeMillis() - startTime);
        return response;
    }
}
