package pengyi.application.report.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.order.representation.OrderRepresentation;
import pengyi.application.report.representation.ReportRepresentation;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.report.Report;

/**
 * Created by liubowen on 2016/3/10.
 * 转换类
 */
@Component
public class ReportRepresentationMapper extends CustomMapper<Report, ReportRepresentation> {
    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Report report, ReportRepresentation representation, MappingContext context) {

        if (null != report.getReportUser()) {

            UserRepresentation date = mappingService.map(report.getReportUser(), UserRepresentation.class, false);

            representation.setReportUser(date);
        }
        if (null != report.getOrder()) {

            OrderRepresentation date = mappingService.map(report.getOrder(), OrderRepresentation.class, false);

            representation.setOrder(date);
        }

    }
}
