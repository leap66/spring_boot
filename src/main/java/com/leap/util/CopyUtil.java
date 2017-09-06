package com.leap.util;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description : CopyUtil
 */
public class CopyUtil {

  public static <T> T copy(T t) {
    String value = GsonUtil.toJson(t);
    if (value == null) {
      return null;
    }
    return GsonUtil.parse(value, (Class<T>) t.getClass());
  }

}
