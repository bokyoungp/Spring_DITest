package org.example.ditest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
① HandlerInterceptor 구현체 생성
        ↓
② WebMvcConfigurer 구현체(WebConfigurer)에 등록
        ↓
③ 세션/쿠키에서 로그인 정보 확인

 */
//public class LoginCheckInterceptor implements HandlerInterceptor {
//
//  @Override
//  public boolean preHandle(HttpServletRequest request,
//                           HttpServletResponse response,
//                           Object handler) throws Exception {
//
//    if (handler instanceof HandlerMethod handlerMethod) {
//    // @LoginRequired 없으면 통과
//        if (handlerMethod.getMethodAnnotation(LoginRequired.class) == null) {
//          return true;
//        }
//    }
//    // 세션에서 로그인 정보 확인
//    HttpSession session = request.getSession(false); // false = 세션 없으면 null 반환
//
//    if (session == null || session.getAttribute("infoUser") == null) {
//      // 미인증 → 로그인 페이지로 리다이렉트
//      response.sendRedirect("/login");
//      return false; // 컨트롤러로 진행 X
//    }
//
//    return true; // 인증됨 → 컨트롤러로 진행
//  }
//}