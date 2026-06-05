package org.example.ditest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("PreHandle ===> RequestMethod : {} , RequestURI : {}", request.getMethod(), request.getRequestURI());
    //log.info("handler info : {} , {}", handler.getClass().getName());
    if(handler instanceof HandlerMethod handlerMethod) {
      log.info("handler info : {} , {}", handlerMethod.getBeanType().getName(), handlerMethod.getMethod().getName());
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    log.info("PostHandle ===> RequestMethod : {} , RequestURI : {}", request.getMethod(), request.getRequestURI());
    if(handler instanceof HandlerMethod handlerMethod) {
      log.info("handler info : {} , {}", handlerMethod.getBeanType().getName(), handlerMethod.getMethod().getName());
    }
    log.info("modelAndView {} ", modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    log.info("AfterComplettion ===> RequestMethod : {} , RequestURI : {}", request.getMethod(), request.getRequestURI());
    if(handler instanceof HandlerMethod handlerMethod) {
      log.info("handler info : {} , {}", handlerMethod.getBeanType().getName(), handlerMethod.getMethod().getName());
    }
    if(ex != null) {
      log.info("Exception {} ", ex.getMessage());
    }
  }
}
