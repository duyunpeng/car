package pengyi.repository.feedback;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.feedback.FeedBack;
import pengyi.domain.model.feedback.IFeedBackRepository;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/9.
 */
@Repository("feedBackRepository")
public class FeedBackRepository extends AbstractHibernateGenericRepository<FeedBack, String>
        implements IFeedBackRepository<FeedBack, String> {
}
