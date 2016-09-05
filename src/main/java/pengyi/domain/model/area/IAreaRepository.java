package pengyi.domain.model.area;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IAreaRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
