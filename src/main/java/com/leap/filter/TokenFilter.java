package com.leap.filter;

import com.leap.cmp.TokenMgr;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.service.connect.IRedisServer;
import com.leap.util.JwtUtil;
import com.leap.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
@Configuration
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
      String userID = TokenMgr.getCurrentUser(httpServletRequest);
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
}
