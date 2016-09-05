package pengyi.application.report;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.report.Report;
import pengyi.domain.service.report.IReportService;
import pengyi.repository.generic.Pagination;

import java.util.List;

/**
 * Created by liubowen on 2016/3/10.
 */
@Service("reportAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ReportAppService implements IReportAppService {

    @Autowired
    private IReportService reportService;

    @Autowired
    private IMappingService mappingService;

    @Override
    @Transactional(readOnly = true)
    public Pagination<ReportRepresentation> pagination(ListReportCommand command) {

        command.verifyPage();

        command.verifyPageSize(20);

        Pagination<Report> pagination = reportService.pagination(command);

        List<ReportRepresentation> data = mappingService.mapAsList(pagination.getData(), ReportRepresentation.class);

        return new Pagination<ReportRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }


    @Override
    public ReportRepresentation show(String reportId) {
        return mappingService.map(reportService.getById(reportId), ReportRepresentation.class, false);
    }

    @Override
    public void handleReport(EditStatusCommand command) {
        reportService.handleReport(command);
    }

    @Override
    public void successReport(EditReportCommand command) {
        reportService.successReport(command);
    }


}
