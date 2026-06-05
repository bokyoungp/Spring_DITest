package org.example.ditest.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionUtils {
  // 쿠키에서 SESSION_ID 추출 후 세션 정보 확인
  public static UserInfo getUserInfoFromCookie(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies();
    if (cookies == null) return null;

    for (Cookie cookie : cookies) {
      if ("SESSION_ID".equals(cookie.getName())) {
        HttpSession session = req.getSession(false);
        if (session != null && session.getId().equals(cookie.getValue())) {
          return (UserInfo) session.getAttribute(SessionInfo.SESSION_NAME);  // "userInfo" 를 상수로 등록
        }
      }
    }
    return null;
  }
}
