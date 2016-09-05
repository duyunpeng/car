package pengyi.repository.redis;

import org.springframework.stereotype.Repository;
import pengyi.repository.redis.generic.AbstractRedisGenericRepository;

/**
 * Created by YJH on 2016/3/21.
 */
@Repository("redisRepository")
public class RedisRepository extends AbstractRedisGenericRepository<String,String> implements IRedisRepository<String,String> {

}
