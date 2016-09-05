package pengyi.repository.withdraw;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.withdraw.IWithdrawRepository;
import pengyi.domain.model.withdraw.Withdraw;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by pengyi on 2016/5/6.
 */
@Repository("withdrawRepository")
public class WithdrawRepository extends AbstractHibernateGenericRepository<Withdraw, String>
        implements IWithdrawRepository<Withdraw, String> {
}
