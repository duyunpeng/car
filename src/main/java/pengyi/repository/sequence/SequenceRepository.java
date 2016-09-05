package pengyi.repository.sequence;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.sequence.ISequenceRepository;
import pengyi.domain.model.sequence.Sequence;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/22.
 */
@Repository("sequenceRepository")
public class SequenceRepository extends AbstractHibernateGenericRepository<Sequence,String>
        implements ISequenceRepository<Sequence,String> {
}
