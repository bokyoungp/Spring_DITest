package org.example.ditest.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor())
        .order(1)
        .addPathPatterns("/posts/**");
//        .excludePathPatterns()

//    registry.addInterceptor(new LogInterceptor2())
//        .order(2)
//        .addPathPatterns("/posts/**");
//
//    registry.addInterceptor(new LogInterceptor3())
//        .order(3)
//        .addPathPatterns("/posts/**");
  }
}
