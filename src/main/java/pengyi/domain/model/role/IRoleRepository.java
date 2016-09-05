package pengyi.domain.model.role;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IRoleRepository<T,ID extends Serializable> extends IHibernateGenericRepository<T,ID> {

    Role getByName(String name);
}
