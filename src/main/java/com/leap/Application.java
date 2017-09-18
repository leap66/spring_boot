package com.leap;

import com.leap.aspect.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public FilterRegistrationBean jwtFilterRegistrationBean() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    TokenFilter tokenFilter = new TokenFilter();
    registrationBean.setFilter(tokenFilter);
    List<String> urlPatterns = new ArrayList<>();
    urlPatterns.add("/auth/login");
    urlPatterns.add("/auth/register");
    urlPatterns.add("/auth/logout");
    registrationBean.setUrlPatterns(urlPatterns);
    return registrationBean;
  }

}
