package com.leap.cmp;

import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.util.JwtUtil;
import com.leap.util.ServletUtil;
import com.leap.util.TokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

  /**
   * 获取当前请求用户ID
   * 
   * @return 当前请求用户ID
   * @throws BaseException
   *           Token空异常
   */
  public static String getCurrentUser(HttpServletRequest request) throws BaseException {
    String token = TokenUtil.getToken(request);
    return JwtUtil.parse(token);
  }
}
