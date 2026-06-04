package org.example.ditest.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
  @Bean
  public FilterRegistrationBean logFilterRegister() {
    FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
    frb.setFilter(new LogFilter());
    frb.setOrder(1);
    frb.addUrlPatterns("/posts/*");
    return frb;
  }

//  @Bean
//  public FilterRegistrationBean logFilter2Register() {
//    FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
//    frb.setFilter(new LogFilter2());
//    frb.setOrder(2);
//    frb.addUrlPatterns("/*");
//    return frb;
//  }
//
//  @Bean
//  public FilterRegistrationBean logFilter2Register() {
//    FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
//    frb.setFilter(new LogFilter3());
//    frb.setOrder(3);
//    frb.addUrlPatterns("/*");
//    return frb;
//  }
}
