package com.leap.aspect;

import com.leap.util.GsonUtil;
import com.leap.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@Aspect
@Component
public class HttpAspect {

  private LogUtil logger = new LogUtil(getClass().getName());

  @Pointcut("execution(public * com.leap.controller..*(..))")
  public void log() {
  }

  @Before("log()")
  public void doBefore(JoinPoint joinPoint) {
    logger.info("开始请求");
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // url
    logger.info("url = {}", request.getRequestURL());
    // ip method
    logger.info("ip + method = {}", request.getRemoteAddr() + "  :  " + request.getMethod());
    // 类方法
    logger.info("class_name = {}",
        joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    // 参数
    logger.info("arg = {}", GsonUtil.toJson(joinPoint.getArgs()));
    // trance_id
    logger.info("trance_id = {}", request.getHeader("trance_id"));
    // mars_cookie
    logger.info("mars_cookie = {}", request.getHeader("mars_cookie"));
    // Cookies
    logger.info("Cookies = {}", GsonUtil.toJson(request.getCookies()));
    // Session
    logger.info("Session = {}", GsonUtil.toJson(request.getSession()));
  }

  @After("log()")
  public void doAfter() {
    logger.info("请求结束");
  }

  @AfterReturning(returning = "object", pointcut = "log()")
  public void doAfterReturning(Object object) {
    logger.info("response = {}", GsonUtil.toJson(object));
  }
}
