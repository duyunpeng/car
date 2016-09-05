package pengyi.application.appversion.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import pengyi.application.appversion.representation.AppVersionRepresentation;
import pengyi.core.util.CoreDateUtils;
import pengyi.domain.model.appversion.AppVersion;

/**
 * Created by YJH on 2016/4/13.
 */
@Component
public class AppVersionRepresentationMapper extends CustomMapper<AppVersion, AppVersionRepresentation> {

    public void mapAtoB(AppVersion appVersion, AppVersionRepresentation representation, MappingContext context) {
        if (null != appVersion.getUpdateDate()) {
            representation.setUpdateDate(CoreDateUtils.formatDate(appVersion.getUpdateDate(), CoreDateUtils.DATETIME));
        }
    }

}
