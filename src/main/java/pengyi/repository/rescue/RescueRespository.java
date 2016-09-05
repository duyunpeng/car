package pengyi.repository.rescue;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.evaluate.Evaluate;
import pengyi.domain.model.rescue.IRescueRepository;
import pengyi.domain.model.rescue.Rescue;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: lvdi
 * Date: 16-3-8
 */
@Repository("rescueRepository")
public class RescueRespository extends AbstractHibernateGenericRepository<Rescue, String> implements IRescueRepository<Rescue, String> {
    @Override
    public Rescue getByName(String rescueName) {
        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("rescueName",rescueName));
        return (Rescue) criteria.uniqueResult();
    }

}
