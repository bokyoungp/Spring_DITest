package org.example.ditest.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ditest.dto.LoginRequestDto;
import org.example.ditest.model.User;
import org.example.ditest.service.UserService;
import org.example.ditest.session.SessionInfo;
import org.example.ditest.session.SessionManager;
import org.example.ditest.session.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
  private final UserService userService;
  private final SessionManager sessionManager;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute LoginRequestDto loginDto,
                      HttpServletRequest req,
                      HttpServletResponse res, RedirectAttributes redirectAttributes) {
    log.info("loginDto {} , {}", loginDto.getUserId(), loginDto.getPassword());

    User user = userService.getUserInfo(loginDto.getUserId());
    log.info("userInfo ==> {}", user);


    if(user != null && loginDto.getPassword().equals(user.getPassword())) {

      //loginCheck(String userId, String rawPassword) ==>  암호화 하는 경우 이 코드 추가

      UserInfo userInfo = new UserInfo(user.getUserId(), user.getUsername());
      // 로그인 검증 : 사용자 id 와 password 가 일치하면 로그인 성공
      // HttpSession 은 사용자(브라우저)별로 서버 측에 독립적으로 생성되는 객체
      // HttpSession 새롭게 생성 한 후 세션에 사용자 정보 저장
      HttpSession session = req.getSession(true);
      sessionManager.save(session.getId(), userInfo);

      // cookie 설정, session 정보 저장, 응답 헤더에 쿠키 정보 담아서
      Cookie cookie = new Cookie("SESSION_ID", session.getId());
      cookie.setHttpOnly(true);
      cookie.setMaxAge(6000);
      res.addCookie(cookie);
//      cookie.setSecure()

      // session 객체에 사용자 정보를 저장해 놓고 로그인 한 사용자정보를 어디서든 꺼내 사용할 수 있게 함
      session.setAttribute(SessionInfo.SESSION_NAME, userInfo); // "userInfo" 를 상수로 등록하고 이를 사용
      return "redirect:/posts/all";
    } else {    // 사용자 id 와 password 가 일치하면 로그인 실패
      return "redirect:/login";
    }
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest req, HttpServletResponse res) {
    // 기존에 세션객체가 존재하면 가져오고 없으면 null 을 반환
    sessionManager.remove(req);

    HttpSession session = req.getSession(false);
    if(session != null) session.invalidate();
    // 존재하는 쿠키 정보 삭제
    Cookie cookie = new Cookie("SESSION_ID", null);
    cookie.setMaxAge(0);
    res.addCookie(cookie);
    return "redirect:/login";
  }
}
