package com.leap.aspect;

import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.service.RedisService;
import com.leap.util.JwtUtil;
import com.leap.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author : ylwei
 * @time : 2017/9/16
 * @description :
 */
@WebFilter(urlPatterns = "/*", filterName = "tokenFilter")
public class TokenFilter implements Filter {

  @Autowired
  private RedisService redisService;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // TODO Auto-generated method stub
    // SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
    // filterConfig.getServletContext());
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException, BaseException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    try {
      String requestToken = "";
      for (Cookie cookie : request.getCookies()) {
        if (MarsConfig.JWT_ID.equals(cookie.getName())) {
          requestToken = cookie.getValue();
          break;
        }
      }
      if (requestToken.length() < 1) {
        String cookie = request.getHeader("Cookie");
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
      String userID = JwtUtil.parse(requestToken);
      String token = redisService.get(RedisUtil.key(userID));
      if (!JwtUtil.parse(token).equals(userID))
        throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
    } catch (Exception e) {
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
    }
  }

  @Override
  public void destroy() {

  }
}
