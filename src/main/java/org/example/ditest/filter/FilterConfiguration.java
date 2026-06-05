package org.example.ditest.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfiguration {

//  @Bean
//  public FilterRegistrationBean logFilterRegister() {
//    FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
//    frb.setFilter(new LogFilter());
//    frb.setOrder(1);
//    frb.addUrlPatterns("/posts/*");
//    return frb;
//  }
//
//  @Bean
//  public FilterRegistrationBean logFilter2Register() {
//    FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
//    frb.setFilter(new HttpMethodOverrideFilter());
//    frb.setOrder(2);
//    frb.addUrlPatterns("/api/v1/posts/*");
//    return frb;
//  }
}

//
//
////
////  @Bean
////  public FilterRegistrationBean logFilter2Register() {
////    FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
////    frb.setFilter(new LogFilter3());
////    frb.setOrder(3);
////    frb.addUrlPatterns("/*");
////    return frb;
////  }
//}
//
//@Configuration
//public class FilterConfiguration implements WebMvcConfigurer {
//  @Bean
//  Filter shallowEtagHeaderFilter() {
//    return new ShallowEtagHeaderFilter();
//  }
//}
