package org.example.ditest.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class SessionManager {
  private final Map<String, UserInfo> sessionStore = new HashMap<>();

  // 세션 저장
  public void save(String sessionId, UserInfo userInfo) {
    sessionStore.put(sessionId, userInfo);
    log.info("세션 저장 : sessionId={}, userInfo={}", sessionId, userInfo);
  }

  // 쿠키에서 SESSION_ID 꺼내서 userInfo 반환
  public UserInfo getUserInfo(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies();
    if (cookies == null) return null;

    for (Cookie cookie : cookies) {
      if ("SESSION_ID".equals(cookie.getName())) {
        return sessionStore.get(cookie.getValue());
      }
    }
    return null;
  }

  // 세션 삭제 (로그아웃)
  public void remove(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies();
    if (cookies == null) return;

    for (Cookie cookie : cookies) {
      if ("SESSION_ID".equals(cookie.getName())) {
        sessionStore.remove(cookie.getValue());
        log.info("세션 삭제 : sessionId={}", cookie.getValue());
      }
    }
  }
}

