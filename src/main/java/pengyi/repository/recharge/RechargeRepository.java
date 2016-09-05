package pengyi.repository.recharge;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.recharge.IRechargeRepository;
import pengyi.domain.model.recharge.Recharge;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by pengyi on 2016/4/11.
 */
@Repository("rechargeRepository")
public class RechargeRepository extends AbstractHibernateGenericRepository<Recharge, String> implements IRechargeRepository<Recharge, String> {
}
