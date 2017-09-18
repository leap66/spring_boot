package com.leap.filter;

import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.service.connect.IRedisServer;
import com.leap.util.JwtUtil;
import com.leap.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
@Service
public class TokenFilter implements HandlerInterceptor {

  private final IRedisServer redisService;

  @Autowired
  public TokenFilter(IRedisServer redisService) {
    this.redisService = redisService;
  }

  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o) throws Exception {
    try {
      String userID = JwtUtil.parse(getToken(httpServletRequest));
      String token = redisService.get(RedisUtil.key(userID));
      if (!JwtUtil.parse(token).equals(userID))
        throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
      return true;
    } catch (Exception e) {
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
    }
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
      throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
  }

  private String getToken(HttpServletRequest httpServletRequest) {
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
      }
    }
    return requestToken;
  }
}
