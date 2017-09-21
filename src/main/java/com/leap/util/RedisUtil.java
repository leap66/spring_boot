package com.leap.util;

import com.leap.config.MarsConfig;

/**
 * @author : ylwei
 * @time : 2017/9/14
 * @description :
 */
public class RedisUtil {

  /**
   * 根据userID生产Token唯一码
   * 
   * @param key
   *          userID
   * @return redis唯一码
   */
  public static String key(String key) {
    return MarsConfig.REDIS_TOKEN_ID + key;
  }
}
