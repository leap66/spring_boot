package com.leap.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
@Configuration
public class MarsConfigurer extends WebMvcConfigurerAdapter {

  private final TokenFilter tokenFilter;

  @Autowired
  public MarsConfigurer(TokenFilter tokenFilter) {
    this.tokenFilter = tokenFilter;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(tokenFilter).addPathPatterns("/leap/**")
        .excludePathPatterns("/leap/app/auth/register")// 注册
        .excludePathPatterns("/leap/app/auth/login")// 登陆
        .excludePathPatterns("/leap/app/auth/logout")// 注销
        .excludePathPatterns("/leap/app/auth/sms/send")// 发送验证码
        .excludePathPatterns("/leap/app/auth/sms/check")// 校验验证码
        .excludePathPatterns("/leap/app/auth/reset/password")// 重置密码
        .excludePathPatterns("/leap/app/auth/delete");// 删除账户
  }
}
