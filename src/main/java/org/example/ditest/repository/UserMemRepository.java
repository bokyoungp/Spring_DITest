package org.example.ditest.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.ditest.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class UserMemRepository {
  private Map<String, User> users = new HashMap<>();
  private int seq = 0;

  public UserMemRepository() {
    User user = new User(++seq, "admin", "admin", "1234", "admin@example.com");
    users.put("admin", user);
    User user1 = new User(++seq, "aaa", "홍길동", "1234", "hong@example.com");
    users.put("aaa", user1);
    User user2 = new User(++seq, "bbb", "김연아", "1234", "kim@example.com");
    users.put("bbb", user2);

    System.out.println(users);
  }

  public User getUserByUserId(String userId) {
    // userId 는 unique 하다 <== biz.rule

    User user = users.get(userId);
    log.info("repository ==> {} ", user);
    return user;
  }

  public User save(User user) {
    user.setId(++seq);
    // unique 한 사용자 id 만 입력할 수 있도록 refactoring 할것
    users.put(user.getUserId(), user);
    return user;
  }

}
