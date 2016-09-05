package pengyi.repository.appversion;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.appversion.AppVersion;
import pengyi.domain.model.appversion.IAppVersionRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/13.
 */
@Repository("appVersionRepository")
public class AppVersionRepository extends AbstractHibernateGenericRepository<AppVersion, String>
        implements IAppVersionRepository<AppVersion, String> {
}
