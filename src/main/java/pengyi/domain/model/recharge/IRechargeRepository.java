package pengyi.domain.model.recharge;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by pengyi on 2016/4/11.
 */
public interface IRechargeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
