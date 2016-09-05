package pengyi.application.permission.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.permission.representation.PermissionRepresentation;
import pengyi.domain.model.permission.Permission;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class PermissionRepresentationMapper extends CustomMapper<Permission, PermissionRepresentation> {
}
