package org.example.ditest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Post {
  private int postId;
  private String title;
  private String body;
  private int likes;
}
