package pengyi.repository.message;

import org.springframework.stereotype.Repository;
import pengyi.domain.model.message.IMessageRepository;
import pengyi.domain.model.message.Message;
import pengyi.repository.generic.AbstractHibernateGenericRepository;

/**
 * Author: pengyi
 * Date: 15-12-30
 */
@Repository("messageRepository")
public class MessageRepository extends AbstractHibernateGenericRepository<Message, String> implements IMessageRepository<Message, String> {
}

