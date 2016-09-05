package pengyi.repository.user.terrace;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.terrace.ITerraceRepository;
import pengyi.domain.model.user.terrace.Terrace;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("terraceRepository")
public class TerraceRepository extends AbstractHibernateGenericRepository<Terrace, String>
        implements ITerraceRepository<Terrace, String> {
}
