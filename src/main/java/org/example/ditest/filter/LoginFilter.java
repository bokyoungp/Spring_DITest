package org.example.ditest.filter;

import org.example.ditest.session.SessionManager;

public class LoginFilter {
//  public LoginFilter(SessionManager sessionManager) {}
}

// Filter에서 세션/토큰을 꺼내 인증 정보를 세팅하고,
// Interceptor에서 로그인 여부를 판단해 미인증 사용자를 로그인 페이지로 보내는 구조로 변경
// AOP는 관리자 권한처럼 메서드 단위로 세분화가 필요할 때 추가