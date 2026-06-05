package org.example.ditest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int id;
  private String userId;
  private String username;
  private String password;
  private String email;
}
