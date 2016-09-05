package pengyi.application.role.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.application.role.representation.RoleRepresentation;
import pengyi.core.mapping.IMappingService;
import pengyi.domain.model.role.Role;

import java.util.List;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class RoleRepresentationMapper extends CustomMapper<Role, RoleRepresentation> {

    @Autowired
    private IMappingService mappingService;

    public void mapAtoB(Role role, RoleRepresentation representation, MappingContext context){
        if(null != role.getPermissions()){
            List<PermissionRepresentation> data = mappingService.mapAsList(role.getPermissions(),PermissionRepresentation.class);
            representation.setPermissions(data);
        }
    }
}
