package pengyi.domain.model.rescue;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by lvdi on 2016/3/8.
 */

public interface IRescueRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    Rescue getByName(String rescueName);
}
