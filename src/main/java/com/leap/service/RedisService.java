package com.leap.service;

import com.leap.service.connect.IRedisServer;
import com.leap.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : ylwei
 * @time : 2017/9/12
 * @description :
 */
@Service
public class RedisService implements IRedisServer {

  private final RedisTemplate<String, ?> redisTemplate;

  @Autowired
  public RedisService(RedisTemplate<String, ?> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public boolean set(final String key, final String value) {
    return redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        connection.set(serializer.serialize(key), serializer.serialize(value));
        return true;
      }
    });
  }

  public String get(final String key) {
    return redisTemplate.execute(new RedisCallback<String>() {
      @Override
      public String doInRedis(RedisConnection connection) throws DataAccessException {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] value = connection.get(serializer.serialize(key));
        return serializer.deserialize(value);
      }
    });
  }

  @Override
  public long del(String key) {
    return redisTemplate.execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        return connection.del(serializer.serialize(key));
      }
    });
  }

  @Override
  public boolean expire(final String key, long expire) {
    return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
  }

  @Override
  public <T> boolean setList(String key, List<T> list) {
    String value = GsonUtil.toJson(list);
    return set(key, value);
  }

  @Override
  public <T> List<T> getList(String key, Class<T> clz) {
    String json = get(key);
    if (json != null) {
      return GsonUtil.parseLst(json, clz);
    }
    return null;
  }

  @Override
  public long lPush(final String key, Object obj) {
    final String value = GsonUtil.toJson(obj);
    return redisTemplate.execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        return connection.lPush(serializer.serialize(key), serializer.serialize(value));
      }
    });
  }

  @Override
  public long rPush(final String key, Object obj) {
    final String value = GsonUtil.toJson(obj);
    return redisTemplate.execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        return connection.rPush(serializer.serialize(key), serializer.serialize(value));
      }
    });
  }

  @Override
  public String lPop(final String key) {
    return redisTemplate.execute(new RedisCallback<String>() {
      @Override
      public String doInRedis(RedisConnection connection) throws DataAccessException {
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        byte[] res = connection.lPop(serializer.serialize(key));
        return serializer.deserialize(res);
      }
    });
  }
}
