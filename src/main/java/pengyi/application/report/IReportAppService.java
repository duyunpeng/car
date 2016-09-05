package pengyi.application.report;

import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.repository.generic.Pagination;

/**
 * Created by liubowen on 2016/3/10.
 */
public interface IReportAppService {

    Pagination<ReportRepresentation> pagination(ListReportCommand command);


    ReportRepresentation show(String reportId);


    void handleReport(EditStatusCommand command);

    void successReport(EditReportCommand command);
}
