package org.example.ditest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ditest.session.SessionManager;
import org.example.ditest.session.UserInfo;
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
public class LoginCheckInterceptor implements HandlerInterceptor {
  private final SessionManager sessionManager;

  public LoginCheckInterceptor(SessionManager sessionManager){
    this.sessionManager = sessionManager;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {

//    if (handler instanceof HandlerMethod handlerMethod) {
//    // @LoginRequired 없으면 통과
//        if (handlerMethod.getMethodAnnotation(LoginRequired.class) == null) {
//          return true;
//        }
//    }
    // 세션저장소에서 로그인 정보 확인 후 로그인 하면 원래 요청 URL 로 돌아가게 하기 위함
    String requestURI = request.getRequestURI();
    UserInfo userInfo = sessionManager.getUserInfo(request);
    if (userInfo == null) { // 로그인 하지 않았을 경우
      response.sendRedirect("/login?redirectURL=" + requestURI);
      return false;
    }
    return true; // 인증됨 → 컨트롤러로 진행
  }
}