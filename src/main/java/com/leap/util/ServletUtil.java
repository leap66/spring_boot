package com.leap.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : ylwei
 * @time : 2017/9/11
 * @description :
 */
public class ServletUtil {

  /**
   * 获取 HttpServletRequest
   *
   * @return Request
   */
  public static HttpServletRequest getRequest() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    return attributes.getRequest();
  }

  /**
   * 获取 HttpServletResponse
   * 
   * @return Response
   */
  public static HttpServletResponse getResponse() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    return attributes.getResponse();
  }
}
