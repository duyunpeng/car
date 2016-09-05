package pengyi.domain.model.feedback;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IFeedBackRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
