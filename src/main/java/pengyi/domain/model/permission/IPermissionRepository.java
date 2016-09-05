package pengyi.domain.model.permission;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/7.
 */
public interface IPermissionRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Permission getByName(String permissionName);
}
