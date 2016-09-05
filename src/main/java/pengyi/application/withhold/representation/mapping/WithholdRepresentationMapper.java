package pengyi.application.withhold.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import pengyi.application.withhold.representation.WithholdRepresentation;
import pengyi.domain.model.withhold.Withhold;

/**
 * Created by pengyi on 2016/5/6.
 */
@Component
public class WithholdRepresentationMapper extends CustomMapper<Withhold, WithholdRepresentation> {

    public void mapAtoB(Withhold withhold, WithholdRepresentation representation, MappingContext context) {
        representation.setUserId(withhold.getBaseUser().getId());
        representation.setUsername(withhold.getBaseUser().getUserName());
    }

}
