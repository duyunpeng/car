package pengyi.domain.model.urlresources;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IUrlResourcesRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    UrlResources getByName(String urlName);
}
