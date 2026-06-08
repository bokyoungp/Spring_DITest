package org.example.ditest.service;

import lombok.RequiredArgsConstructor;
import org.example.ditest.model.User;
import org.example.ditest.repository.UserMemRepository;
import org.example.ditest.security.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserMemRepository repository;
  //private final PasswordEncoder passwordEncoder;

  public User getUserInfo(String userId) {
    return repository.getUserByUserId(userId);
  }

  public User createUser(User user) {
// security
//    String encodedPassword = passwordEncoder.encode(user.getPassword());
//    user.setPassword(encodedPassword);
    // 순수 자바 코드
    // user.setPassword(PasswordUtil.encode(user.getPassword()));
    return repository.save(user);
  }

  // 로그인 검증 시
//  public boolean loginCheck(String userId, String rawPassword) {
//    User user = repository.getUserByUserId(userId);
//    return PasswordUtil.matches(rawPassword, user.getPassword());
//  }
}
