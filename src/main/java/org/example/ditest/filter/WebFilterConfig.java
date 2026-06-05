package org.example.ditest.filter;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebFilterConfig implements WebMvcConfigurer {
  @Bean
  Filter shallowEtagFilter() {
    return new ShallowEtagHeaderFilter();
  }
}
