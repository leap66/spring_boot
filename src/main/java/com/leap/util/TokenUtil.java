package com.leap.util;

import com.leap.config.MarsConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : ylwei
 * @time : 2018/1/23
 * @description :
 */
public class TokenUtil {

  /**
   * 获取请求token
   */
  public static String getToken(HttpServletRequest httpServletRequest) {
    String requestToken = "";
    for (Cookie cookie : httpServletRequest.getCookies()) {
      if (MarsConfig.JWT_ID.equals(cookie.getName())) {
        requestToken = cookie.getValue();
        break;
      }
    }
    if (requestToken.length() < 1) {
      String cookie = httpServletRequest.getHeader("Cookie");
      if (cookie.contains(";")) {
        String[] cookies = cookie.split(";");
        for (String temp : cookies) {
          if (temp.contains("jwt=")) {
            requestToken = temp.substring(5, temp.length());
            break;
          }
        }
      } else if (cookie.contains("jwt=")) {
        requestToken = cookie.substring(5, cookie.length());
      }
    }
    return requestToken;
  }
}
