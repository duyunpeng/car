package pengyi.application.user.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.role.representation.RoleRepresentation;
import pengyi.application.user.representation.BaseUserRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.user.BaseUser;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class BaseUserRepresentationMapper extends CustomMapper<BaseUser, BaseUserRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(BaseUser baseUser, BaseUserRepresentation representation, MappingContext context) {
        if (null != baseUser.getUserRole()) {
            RoleRepresentation data = mappingService.map(baseUser.getUserRole(), RoleRepresentation.class, false);
            representation.setUserRole(data);
        }
    }

}
