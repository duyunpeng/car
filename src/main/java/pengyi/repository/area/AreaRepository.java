package pengyi.repository.area;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.area.Area;
import pengyi.domain.model.area.IAreaRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/9.
 */
@Repository("areaRepository")
public class AreaRepository extends AbstractHibernateGenericRepository<Area, String>
        implements IAreaRepository<Area, String> {
}
