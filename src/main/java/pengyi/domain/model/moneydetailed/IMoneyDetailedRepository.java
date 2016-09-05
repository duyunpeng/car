package pengyi.domain.model.moneydetailed;

import pengyi.application.moneydetailed.command.ListMoneyDetailedCommand;
import pengyi.repository.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/9.
 */
public interface IMoneyDetailedRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Number sum(ListMoneyDetailedCommand command);
}
