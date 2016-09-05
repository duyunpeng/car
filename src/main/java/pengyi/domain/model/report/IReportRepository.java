package pengyi.domain.model.report;

import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by liubowen on 2016/03/07.
 */

public interface IReportRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
