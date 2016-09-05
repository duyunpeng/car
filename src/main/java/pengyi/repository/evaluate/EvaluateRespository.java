package pengyi.repository.evaluate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.evaluate.IEvaluateRepository;
import pengyi.domain.model.order.Order;
import pengyi.repository.generic.AbstractHibernateGenericRepository;


/**
 * Author: lvdi
 * Date: 15-3-7
 */
@Repository("evaluateRepository")
public class EvaluateRespository extends AbstractHibernateGenericRepository<Evaluate, String> implements IEvaluateRepository<Evaluate, String> {

    @Override
    public Evaluate getByOrder(String orderId, String userId) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("order.id", orderId));
        criteria.add(Restrictions.eq("evaluateUser.id", userId));
        return (Evaluate) criteria.uniqueResult();
    }

    @Override
    public Double reckonDriverLevel(String driverId) {
        Criteria criteria = getSession().createCriteria(getPersistentClass()).setProjection(Projections.avg("level"));
        criteria.add(Restrictions.eq("order.receiveUser.id", driverId));
        criteria.createAlias("order", "order");
        criteria.createAlias("order.receiveUser", "receiveUser");
        return (Double) criteria.uniqueResult();
    }


}