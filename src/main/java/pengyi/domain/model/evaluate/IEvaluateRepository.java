package pengyi.domain.model.evaluate;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by lcdi on 2016/3/7.
 */

public interface IEvaluateRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {


    Evaluate getByOrder(String orderId,String userId);


    Double reckonDriverLevel(String driverId);
}
