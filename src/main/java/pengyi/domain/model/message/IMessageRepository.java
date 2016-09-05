package pengyi.domain.model.message;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by liubowen on 2016/03/07.
 */

public interface IMessageRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
