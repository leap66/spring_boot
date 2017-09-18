package com.leap.service.connect;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/14
 * @description :
 */
public interface IRedisServer {

  public boolean set(String key, String value);

  public String get(String key);

  public long del(String key);

  public boolean expire(String key, long expire);

  public <T> boolean setList(String key, List<T> list);

  public <T> List<T> getList(String key, Class<T> clz);

  public long lPush(String key, Object obj);

  public long rPush(String key, Object obj);

  public String lPop(String key);
}
