package pengyi.repository.redis.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by YJH on 2016/3/21.
 */
@Repository
public class AbstractRedisGenericRepository<K,V> implements IRedisGenericRepository<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public long push(final K key, final V value) {
        return redisTemplate.boundListOps(key).rightPush(value);
    }

    public void addCache(final K key, final V value, final long timeout, final TimeUnit timeUnit) {
        redisTemplate.delete(key);
        BoundValueOperations operations = redisTemplate.boundValueOps(key);
        operations.set(value);
        operations.expire(timeout, timeUnit);
    }

    public V getCache(final K key) {
        return redisTemplate.boundValueOps(key).get();
    }

    @Override
    public V pop(final K key, long timeout, TimeUnit unit) {
        return redisTemplate.boundListOps(key).leftPop(timeout, unit);
    }

    @Override
    public void remove(K key) {
        redisTemplate.delete(key);
    }

    @Override
    public void flushAll() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Override
    public boolean exists(final K key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public long size(final K key) {
        return redisTemplate.boundListOps(key).size();

    }

}
