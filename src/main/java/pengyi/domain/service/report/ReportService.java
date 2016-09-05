package pengyi.domain.service.report;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.report.command.CreateReportCommand;
import pengyi.application.report.command.EditReportCommand;
import pengyi.application.report.command.ListReportCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.ReportStatus;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.order.Order;
import pengyi.domain.model.report.IReportRepository;
import pengyi.domain.model.report.Report;
import pengyi.domain.model.user.BaseUser;
import pengyi.domain.model.user.driver.Driver;
import pengyi.domain.service.order.IOrderService;
import pengyi.domain.service.user.IBaseUserService;
import pengyi.domain.service.user.driver.IDriverService;
import pengyi.domain.service.user.user.IUserService;
import pengyi.repository.generic.Pagination;
import pengyi.repository.report.ReportRepository;

import java.util.*;

/**
 * Created by liubowen on 2016/3/7.
 */
@Service("reportService")
public class ReportService implements IReportService {

    @Autowired
    private IReportRepository<Report, String> reportRepository;

    @Autowired
    private IBaseUserService baseUserService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IDriverService driverService;

    @Autowired
    private IUserService userService;

    @Override
    public void createReport(CreateReportCommand command) {

        BaseUser baseUser = baseUserService.show(command.getReportUser());

        Order order = orderService.show(command.getOrderId());

        Report report = new Report(baseUser, order, new Date(), null, null, command.getDescription(), ReportStatus.PENDING, null);

        reportRepository.save(report);

        if (null != order.getReceiveUser()) {
            driverService.updateReportCount(order.getReceiveUser().getId());
        }
        userService.updateReportCount(baseUser.getId());
    }

    @Override
    public Report getById(String reportId) {
        Report report = reportRepository.getById(reportId);
        if (report == null) {
            throw new NoFoundException("没有找到id=[" + reportId + "]的记录");
        }
        return report;
    }

    @Override
    public Pagination<Report> pagination(ListReportCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        if (!CoreStringUtils.isEmpty(command.getReportUser())) {
            criterionList.add(Restrictions.like("reportUser.userName", command.getReportUser(), MatchMode.ANYWHERE));
            aliasMap.put("reportUser", "reportUser");
        }
        if (!CoreStringUtils.isEmpty(command.getOrderNumber())) {
            aliasMap.put("order", "order");
            criterionList.add(Restrictions.like("order.orderNumber", command.getOrderNumber(), MatchMode.ANYWHERE));
        }

        if (!CoreStringUtils.isEmpty(command.getStartDealTime())) {
            criterionList.add(Restrictions.ge("reportTime", CoreDateUtils.parseDate(command.getStartDealTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDealTime())) {
            criterionList.add(Restrictions.le("reportTime", CoreDateUtils.parseDate(command.getEndDealTime(), "yyyy/MM/dd HH:mm")));
        }
//        if (!CoreStringUtils.isEmpty(command.getEndDealTime()) && !CoreStringUtils.isEmpty(command.getStartDealTime())) {
//            criterionList.add(Restrictions.between("reportTime", CoreDateUtils.parseDate(command.getStartDealTime()), CoreDateUtils.parseDate(command.getEndDealTime())));
//        }
        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("reportTime"));
        return reportRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);
    }

    @Override
    public void apiFinishReport(EditReportCommand command) {
        Report report = this.getById(command.getId());
        if (report.getStatus() == ReportStatus.IN_PROCESS) {
            report.setHandleResult(command.getHandleResult());
            report.setStatus(ReportStatus.FIGURE_OUT);
            report.setEndDealTime(new Date());
            reportRepository.update(report);
        }

    }

    @Override
    public void apiUpdateReport(EditReportCommand command) {
        Report report = this.getById(command.getId());
        if (report.getStatus() == ReportStatus.PENDING) {
            report.setStatus(ReportStatus.IN_PROCESS);
            report.setStartDealTime(new Date());
            reportRepository.update(report);
        }
    }

    @Override
    public Pagination<Report> apiPagination(ListReportCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();
        criterionList.add(Restrictions.eq("reportUser.id", command.getReportUser()));

        if (!CoreStringUtils.isEmpty(command.getOrderNumber())) {
            criterionList.add(Restrictions.eq("order.orderNumber", command.getOrderNumber()));
            aliasMap.put("order", "order");
        }
        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }

        List<org.hibernate.criterion.Order> orderList = new ArrayList<org.hibernate.criterion.Order>();
        orderList.add(org.hibernate.criterion.Order.desc("reportTime"));
        return reportRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null, null);
    }

    @Override
    public void handleReport(EditStatusCommand command) {
        Report report = this.getById(command.getId());
        report.fainWhenConcurrencyViolation(command.getVersion());

        report.setStatus(ReportStatus.IN_PROCESS);
        report.setStartDealTime(new Date());

        reportRepository.update(report);
    }

    @Override
    public void successReport(EditReportCommand command) {
        Report report = this.getById(command.getId());
        report.fainWhenConcurrencyViolation(command.getVersion());

        report.setStatus(ReportStatus.FIGURE_OUT);
        report.setEndDealTime(new Date());
        report.setHandleResult(command.getHandleResult());

        reportRepository.update(report);
    }

}
