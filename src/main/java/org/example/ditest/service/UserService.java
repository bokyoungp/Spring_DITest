package org.example.ditest.service;

import lombok.RequiredArgsConstructor;
import org.example.ditest.model.User;
import org.example.ditest.repository.UserMemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserMemRepository repository;
  public User getUserInfo(String userId) {
    return repository.getUserByUserId(userId);
  }

  public User createUser(User user) {
    return repository.save(user);
  }
}
