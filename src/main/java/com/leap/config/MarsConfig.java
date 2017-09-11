package com.leap.config;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public class MarsConfig {

  // debug 模式
  public static final boolean debug = true;

  // 敏感操作，需要授权
  public static final boolean secret = true;

  // Token相关配置
  public static final String JWT_ID = "jwt";
  public static final String JWT_SECRET = "weiyaling@hd123.com";
  public static final long JWT_ttlMillis = 12 * 60 * 60 * 1000;
}
