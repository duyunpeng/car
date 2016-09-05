package pengyi.domain.model.user;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * update by YJH
 *
 * Created by pengyi on 2015/12/24.
 */
public interface IBaseUserRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    BaseUser getByUserName(String userName);

}
