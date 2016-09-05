package pengyi.repository.urlresources;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.urlresources.IUrlResourcesRepository;
import pengyi.domain.model.urlresources.UrlResources;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("urlResourcesRepository")
public class UrlResourcesRepository extends AbstractHibernateGenericRepository<UrlResources, String>
        implements IUrlResourcesRepository<UrlResources, String> {
    @Override
    public UrlResources getByName(String urlName) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("urlName",urlName));
        return (UrlResources) criteria.uniqueResult();
    }
}
