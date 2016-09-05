package pengyi.application.user.user.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.user.user.representation.UserRepresentation;
import pengyi.domain.model.user.user.User;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class UserRepresentationMapper extends CustomMapper<User, UserRepresentation> {
}
