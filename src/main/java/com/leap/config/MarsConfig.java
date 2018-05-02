package com.leap.config;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public class MarsConfig {

  // debug 模式
  public static final boolean debug = true;
  private static final long DateTime = 24 * 60 * 60 * 1000;

  // 敏感操作，需要授权
  public static final boolean secret = true;

  // Token相关配置
  public static final String JWT_ID = "jwt";
  public static final String JWT_SECRET = "weiyaling@hd123.com";
  public static final long JWT_ttlMillis = DateTime;

  // Redis相关配置
  public static final String REDIS_TOKEN_ID = "marsRedisToken";
  public static final String REDIS_BAI_DU_TOKEN = "marsRedisTokenBaiDu";

  // 图灵相关配置
  public static final String TU_LING_KEY = "eec85c378bec4dc9983ad98fda728f6e";
  public static final String TU_LING_URL = "http://www.tuling123.com/openapi/api";

  // 百度相关配置
  public static String BAI_DU_TOKEN;
  public static final long BAI_DU_ttlMillis = 30 * DateTime;
  public static final String BAI_DU_TOKEN_URL = "https://openapi.baidu.com/oauth/2.0/token";
  public static final String BAI_DU_VOP_URL = "http://vop.baidu.com/server_api";
  public static final String BAI_DU_TTS_URL = "http://tsn.baidu.com/text2audio";
  public static final String BAI_DU_API_KEY = "PFnlmgu2vYg1IKQVDLqoVQkQ";
  public static final String BAI_DU_SECRET_KEY = "3895f0ca1dd0dbe2d740901b1a652a6d";

}
