package pengyi.repository.withhold;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.withhold.IWithholdRepository;
import pengyi.domain.model.withhold.Withhold;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by pengyi on 2016/5/6.
 */
@Repository("withholdRepository")
public class WithholdRepository extends AbstractHibernateGenericRepository<Withhold, String>
        implements IWithholdRepository<Withhold, String> {
}
