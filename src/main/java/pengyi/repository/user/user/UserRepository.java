package pengyi.repository.user.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pengyi.domain.model.user.user.IUserRepository;
import pengyi.domain.model.user.user.User;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/7.
 */
@Repository("userRepository")
public class UserRepository extends AbstractHibernateGenericRepository<User, String>
        implements IUserRepository<User, String> {
}
