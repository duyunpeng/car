package pengyi.application.feedback.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;
import pengyi.application.feedback.representation.FeedBackRepresentation;
import pengyi.domain.model.feedback.FeedBack;

/**
 * Created by YJH on 2016/3/9.
 */
@Component
public class FeedBackRepresentationMapper extends CustomMapper<FeedBack,FeedBackRepresentation> {
}
