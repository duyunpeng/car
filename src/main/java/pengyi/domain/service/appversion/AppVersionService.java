package pengyi.domain.service.appversion;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pengyi.application.appversion.command.CreateAppVersionCommand;
import pengyi.application.appversion.command.ListAppVersionCommand;
import pengyi.core.commons.command.EditStatusCommand;
import pengyi.core.exception.NoFoundException;
import pengyi.core.type.EnableStatus;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.appversion.AppVersion;
import pengyi.domain.model.appversion.IAppVersionRepository;
import pengyi.repository.generic.Pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/4/13.
 */
@Service("appVersionService")
public class AppVersionService implements IAppVersionService {

    @Autowired
    private IAppVersionRepository<AppVersion, String> appVersionRepository;

    @Override
    public Pagination<AppVersion> pagination(ListAppVersionCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        if (!CoreStringUtils.isEmpty(command.getAppVersion())) {
            criterionList.add(Restrictions.like("appVersion", command.getAppVersion(), MatchMode.ANYWHERE));
        }
        if (!CoreStringUtils.isEmpty(command.getStartUpdateDate()) && null != CoreDateUtils.parseDate(command.getStartUpdateDate(),"yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("updateDate", CoreDateUtils.parseDate(command.getStartUpdateDate(),"yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndUpDateDate()) && null != CoreDateUtils.parseDate(command.getEndUpDateDate(),"yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("updateDate", CoreDateUtils.parseDate(command.getEndUpDateDate(),"yyyy/MM/dd HH:mm")));
        }
        if (null != command.getStatus()) {
            criterionList.add(Restrictions.eq("status", command.getStatus()));
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("updateDate"));
        return appVersionRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList);
    }

    @Override
    public AppVersion create(CreateAppVersionCommand command) {
        AppVersion appVersion = new AppVersion(command.getAppVersion(), new Date(), command.getUpdateContent(), command.getStatus());
        appVersionRepository.save(appVersion);
        return appVersion;
    }

    @Override
    public void updateStatus(EditStatusCommand command) {
        AppVersion appVersion = this.searchByID(command.getId());
        appVersion.fainWhenConcurrencyViolation(command.getVersion());
        if (appVersion.getStatus() == EnableStatus.ENABLE) {
            appVersion.setStatus(EnableStatus.DISABLE);
        } else {
            appVersion.setStatus(EnableStatus.ENABLE);
        }
        appVersionRepository.update(appVersion);
    }

    @Override
    public AppVersion searchByID(String id) {
        AppVersion appVersion = appVersionRepository.getById(id);
        if (null == appVersion) {
            throw new NoFoundException("没有找到AppVersion Id=[" + id + "]的记录");
        }
        return appVersion;
    }

    @Override
    public AppVersion searchNewVersion() {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("status", EnableStatus.ENABLE));
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("updateDate"));
        return appVersionRepository.list(criterionList, orderList).get(0);
    }

}
