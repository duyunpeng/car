package pengyi.domain.model.order;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pengyi on 2016/4/27.
 */
public interface IOrderWayPointRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
