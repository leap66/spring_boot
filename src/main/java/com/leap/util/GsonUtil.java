package com.leap.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.leap.config.MarsConfig;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public class GsonUtil {

  private static Gson instance = getInstance();

  /**
   * 将json字符串转为 java对象
   */
  synchronized public static <T> T parse(String json, Class<T> classOfT) {
    return instance.fromJson(json, classOfT);
  }

  /**
   * 将json字符串转为 java列表对象
   */
  synchronized public static <T> List<T> parseLst(String json, Class<T> classOfT) {
    return instance.fromJson(json, new TypeToken<List<T>>() {
    }.getType());
  }

  /**
   * 将obj对象转为json格式数据
   */
  synchronized public static String toJson(Object obj) {
    if (null == obj)
      return "";
    return instance.toJson(obj);
  }

  private static Gson getInstance() {
    if (null == instance) {
      if (MarsConfig.debug) {
        instance = new GsonBuilder()
            // 序列化null
            .serializeNulls()
            // 设置日期时间格式，另有2个重载方法
            // 在序列化和反序化时均生效
            // .setDateFormat("yyyy-MM-dd HH:mm:ss")
            // 基于注解 @Expose(deserialize = true,serialize = true) 序列化和反序列化都都生效
            // .excludeFieldsWithoutExposeAnnotation()
            // 基于访问修饰符
            // .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC)
            // 禁此序列化内部类
            .disableInnerClassSerialization()
            // 生成不可执行的Json（多了 )]}' 这4个字符）
            // .generateNonExecutableJson()
            // 禁止转义html标签
            .disableHtmlEscaping()
            // 格式化输出
            .setPrettyPrinting()
            // 创建
            .create();
      } else {
        instance = new Gson();
      }
    }
    return instance;
  }
}
