package pengyi.application.user.terrace.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.user.terrace.representation.TerraceRepresentation;
import pengyi.domain.model.user.terrace.Terrace;

/**
 * Created by YJH on 2016/3/7.
 */
@Component
public class TerraceRepresentationMapper extends CustomMapper<Terrace, TerraceRepresentation> {
}
