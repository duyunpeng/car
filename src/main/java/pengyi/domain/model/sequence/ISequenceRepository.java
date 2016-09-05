package pengyi.domain.model.sequence;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/22.
 */
public interface ISequenceRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
