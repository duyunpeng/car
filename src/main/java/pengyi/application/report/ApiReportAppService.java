package pengyi.application.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.core.api.ResponseCode;
import pengyi.core.api.ResponseMessage;
import pengyi.core.mapping.IMappingService;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.message.Message;
import pengyi.domain.model.report.Report;
import pengyi.domain.service.report.IReportService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/16.
 */
@Service("apiReportService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiReportAppService implements IApiReportAppService {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IMappingService mappingService;


    @Override
    @Transactional(readOnly = true)
    public ReportRepresentation show(String reportId) {
        Report report = reportService.getById(reportId);
        if (null != report) {
            return mappingService.map(report, ReportRepresentation.class, false);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse list(ListReportCommand command) {
        if (null != command) {

            Pagination<Report> pagination = reportService.pagination(command);
            List<ReportRepresentation> data = mappingService.mapAsList(pagination.getData(), ReportRepresentation.class);
            Pagination<ReportRepresentation> result = new Pagination<ReportRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, result, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());

        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }

    @Override
    public BaseResponse create(CreateReportCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getOrderId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10024.getMessage());
            }
            if (CoreStringUtils.isEmpty(command.getDescription())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_20007.getMessage());
            }
            reportService.createReport(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        }
        return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
    }

    @Override
    public BaseResponse updateReport(EditReportCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            reportService.apiUpdateReport(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());

        }
    }

    @Override
    public BaseResponse finishReport(EditReportCommand command) {
        if (null != command) {
            if (CoreStringUtils.isEmpty(command.getId())) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
            }
            if (null == command.getVersion()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10001.getMessage());
            }
            if (null == command.getHandleResult()) {
                return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_60001.getMessage());
            }
            reportService.apiFinishReport(command);
            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, null, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());

        }
    }

    @Override
    public BaseResponse apiPagination(ListReportCommand command) {
        if (null != command) {
            command.verifyPage();
            command.verifyPageSize(10);
            Pagination<Report> pagination = reportService.apiPagination(command);
            List<ReportRepresentation> data = mappingService.mapAsList(pagination.getData(), ReportRepresentation.class);

            return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0,
                    new Pagination<ReportRepresentation>(data, pagination.getCount(), pagination.getPage(),
                            pagination.getPageSize()),
                    ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
        } else {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseCode.RESPONSE_CODE_PARAMETER_ERROR.getMessage());
        }
    }

    @Override
    public BaseResponse apiShow(String id) {
        if (CoreStringUtils.isEmpty(id)) {
            return new BaseResponse(ResponseCode.RESPONSE_CODE_PARAMETER_ERROR, 0, null, ResponseMessage.ERROR_10000.getMessage());
        }
        ReportRepresentation data = mappingService.map(reportService.getById(id), ReportRepresentation.class, false);
        return new BaseResponse(ResponseCode.RESPONSE_CODE_SUCCESS, 0, data, ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }
}

