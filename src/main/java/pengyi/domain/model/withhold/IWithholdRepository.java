package pengyi.domain.model.withhold;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi on 2016/5/6.
 */
public interface IWithholdRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
