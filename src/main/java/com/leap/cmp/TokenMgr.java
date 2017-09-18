package com.leap.cmp;

import com.leap.config.MarsConfig;
import com.leap.util.ServletUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : ylwei
 * @time : 2017/9/10
 * @description :
 */
public class TokenMgr {

  /**
   * 根据response、UserID设置Token
   *
   * @param token
   *          Token
   */
  public static void setToken(String token) {
    HttpServletResponse response = ServletUtil.getResponse();
    Cookie cookie = new Cookie(MarsConfig.JWT_ID, token);
    cookie.setPath("/leap");
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
  }

  /**
   * 根据response 清除 Token
   */
  public static void clearToken() {
    HttpServletResponse response = ServletUtil.getResponse();
    Cookie cookie = new Cookie(MarsConfig.JWT_ID, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }
}
