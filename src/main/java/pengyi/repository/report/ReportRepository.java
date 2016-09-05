package pengyi.repository.report;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.report.IReportRepository;
import pengyi.domain.model.report.Report;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: liubowen
 * Date: 2016-03-07 16:22:18
 */
@Repository("reportRepository")
public class ReportRepository extends AbstractHibernateGenericRepository<Report, String> implements IReportRepository<Report, String> {
}

