package pengyi.domain.model.appversion;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/13.
 */
public interface IAppVersionRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
