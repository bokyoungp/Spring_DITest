package org.example.ditest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String method = req.getMethod();
    String requestURI = req.getRequestURI();
    log.info("Request Method : RequestURI ==> {}, {}", method, requestURI);
    chain.doFilter(request, response);
  }
}
