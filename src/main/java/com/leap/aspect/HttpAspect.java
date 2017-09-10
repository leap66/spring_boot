package com.leap.aspect;

import com.leap.model.Trace;
import com.leap.util.GsonUtil;
import com.leap.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@Aspect
@Component
public class HttpAspect {

  private LogUtil logger = new LogUtil(getClass().getName());
  private Trace trace;

  @Pointcut("execution(public * com.leap.controller..*(..))")
  public void log() {
  }

  @Before("log()")
  public void doBefore(JoinPoint joinPoint) {
    logger.info("开始请求: ");
    trace = new Trace();
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    trace.setId(UUID.randomUUID().toString());
    trace.setCreated(new Date());
    // url
    trace.setUrl(request.getRequestURL().toString());
    // ip method
    trace.setIpId(request.getRemoteAddr());
    // ip method
    trace.setMethod(request.getMethod());
    // 类方法
    trace.setClassName(
        joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    // 参数
    trace.setArg(GsonUtil.toJson(joinPoint.getArgs()));
    // trance_id
    trace.setTraceId(request.getHeader("trance_id"));
    // mars_cookie
    trace.setMarsCookie(request.getHeader("mars_cookie"));
    // Cookies
    trace.setCookies(GsonUtil.toJson(request.getCookies()));
    // Session
    trace.setSession(GsonUtil.toJson(request.getCookies()));
    logger.info("request = {}", GsonUtil.toJson(trace));
  }

  @After("log()")
  public void doAfter() {
    logger.info("请求结束: ");
  }

  @AfterReturning(returning = "object", pointcut = "log()")
  public void doAfterReturning(Object object) {
    trace.setResponse(GsonUtil.toJson(object));
    trace.setEnd(new Date());
    // traceService.save(trace);
    logger.info("response = {}", trace.getResponse());
  }
}
