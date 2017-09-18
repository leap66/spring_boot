package com.leap.util;

import java.util.List;
import java.util.Set;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description : IsEmpty
 */
public class IsEmpty {

  // 判断list是否为空或者null
  public static boolean list(List object) {
    return (null == object || object.size() == 0);
  }

  // 判断所有的list是否都为空或者null
  public static boolean list(List... objects) {
    boolean allNull = true;
    for (List obj : objects) {
      if (null != obj && obj.size() > 0) {
        allNull = false;
        break;
      }
    }
    return allNull;
  }

  public static boolean list(Set object) {
    return (null == object || object.size() == 0);
  }

  public static boolean string(String object) {
    return (null == object || "".equals(object));
  }

  public static boolean string(String... objects) {
    boolean existEmpty = false;
    for (String s : objects) {
      if (null == s || "".equals(s)) {
        existEmpty = true;
        break;
      }
    }
    return existEmpty;
  }

  public static boolean object(Object object) {
    return (null == object);
  }
}
