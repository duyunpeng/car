package pengyi.repository.moneydetailed;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;
import pengyi.domain.model.moneydetailed.IMoneyDetailedRepository;
import pengyi.domain.model.moneydetailed.MoneyDetailed;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

import java.util.Calendar;
import java.util.List;

/**
 * Created by YJH on 2016/3/9.
 */
@Repository("moneyDetailedRepository")
public class MoneyDetailedRepository extends AbstractHibernateGenericRepository<MoneyDetailed, String>
        implements IMoneyDetailedRepository<MoneyDetailed, String> {
    @Override
    public Number sum(ListMoneyDetailedCommand command) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());

        if (!CoreStringUtils.isEmpty(command.getUserId())) {
            criteria.add(Restrictions.eq("baseUser.id", command.getUserId()));
        }

        if (null != command.getFlowType()) {
            criteria.add(Restrictions.eq("flowType", command.getFlowType()));
        }

        if (!CoreStringUtils.isEmpty(command.getDate())) {
            String[] sp = command.getDate().split("-");
            if (sp.length == 2) {
                Calendar start = Calendar.getInstance();
                start.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, 1);
                start.set(Calendar.HOUR_OF_DAY, 0);
                start.set(Calendar.MINUTE, 0);
                start.set(Calendar.SECOND, 0);

                Calendar end = Calendar.getInstance();
                end.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]) - 1, 1);
                end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
                end.set(Calendar.HOUR_OF_DAY, 23);
                end.set(Calendar.MINUTE, 59);
                end.set(Calendar.SECOND, 59);
                criteria.add(Restrictions.between("createDate", start.getTime(), end.getTime()));
            } else {
                criteria.add(Restrictions.between("createDate", CoreDateUtils.parseDateStart(command.getDate()), CoreDateUtils.parseDateEnd(command.getDate())));
            }
        }

        criteria.setProjection(Projections.sum("money"));
        return (Number) criteria.uniqueResult();
    }
}
