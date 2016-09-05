package pengyi.domain.model.car;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by lcdi on 2016/3/7.
 */

public interface ICarRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Car getByNumber(String carNumber);

    Car getByDriver(String driver);

}
