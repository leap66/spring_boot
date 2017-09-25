package com.leap.util;

import com.google.gson.*;
import com.leap.config.MarsConfig;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
  synchronized public static <T> T parse(String json, Type classOfT) {
    return instance.fromJson(json, classOfT);
  }

  /**
   * 将json字符串转为 java列表对象
   */
  synchronized public static <T> List<T> parseLst(String json, Type classOfT) {
    return instance.fromJson(json, classOfT);
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
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .registerTypeAdapter(String.class, new StringConverter())
            .registerTypeAdapter(Date.class, new DateConverter())
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

  /**
   * 实现了 序列化 接口 对为null的字段进行转换
   */
  private static class StringConverter implements JsonSerializer<String>, JsonDeserializer<String> {
    // 字符串为null 转换成"",否则为字符串类型
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (IsEmpty.object(json.getAsJsonPrimitive()))
        return null;
      return json.getAsJsonPrimitive().getAsString();
    }

    @Override
    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
      return src == null || src.equals("null") ? new JsonPrimitive("") : new JsonPrimitive(src);
    }
  }

  private static class DateConverter implements JsonDeserializer<Date>, JsonSerializer<Date> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      if (IsEmpty.object(json.getAsString()))
        return null;
      try {
        return sdf.parse(json.getAsString());
      } catch (ParseException e) {
        return null;
      }
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
      if (IsEmpty.object(src))
        return null;
      return new JsonPrimitive(sdf.format(src));
    }
  }
}
