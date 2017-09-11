package com.leap.cmp;

import com.leap.config.MarsConfig;
import com.leap.util.JwtUtil;
import com.leap.util.ServletUtil;

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
   * 根据request拿到UserID
   * 
   * @return userID
   */
  public static String getUserId() {
    HttpServletRequest request = ServletUtil.getRequest();
    return JwtUtil.parse(request.getHeader("Cookie"));
  }

  /**
   * 根据response设置UserID
   * 
   * @param userId
   *          userID
   */
  public static void setUserId(String userId) {
    HttpServletResponse response = ServletUtil.getResponse();
    Cookie cookie = new Cookie(MarsConfig.JWT_ID, JwtUtil.created(userId));
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
  }
}
