package pengyi.application.report;

import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.api.BaseResponse;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/16.
 */
public interface IApiReportAppService {
    ReportRepresentation show(String reportId);

    BaseResponse list(ListReportCommand command);

    BaseResponse create(CreateReportCommand command);

    BaseResponse updateReport(EditReportCommand command);

    BaseResponse finishReport(EditReportCommand command);

    BaseResponse apiPagination(ListReportCommand command);

    BaseResponse apiShow(String id);
}
