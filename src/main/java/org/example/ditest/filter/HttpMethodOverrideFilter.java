package org.example.ditest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class HttpMethodOverrideFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    log.info("요청 메서드 {}, 요청 URI {} ", req.getMethod(), req.getRequestURI());
    String method = req.getHeader("X-HTTP-Method-Override");
    log.info("X-HTTP-Method-Override {} ", method);
    if(method != null && !(method.isEmpty())) {
      HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
        @Override
        public String getMethod() {
          return method;
        }
      };
      chain.doFilter(wrapper, response);
    } else {
      chain.doFilter(request, response);
    }
  }
}

//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//    HttpServletRequest req = (HttpServletRequest) request;
//    String methodOverride = req.getHeader("X-HTTP-Method-Override");
//    log.info("원본 요청 method ==> {} , 변경된 method ==> {} ", req.getMethod(), methodOverride);
//
//    if(methodOverride != null && !(methodOverride.isEmpty())) {
//      HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) {
//        @Override
//        public String getMethod() {
//          return methodOverride;
//        }
//      };
//      chain.doFilter(wrapper, response);
//    } else {
//      chain.doFilter(request, response);
//    }
//
//  }
//}
