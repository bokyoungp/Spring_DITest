package org.example.ditest.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class PostMemRepository implements PostRepository{

  public String getAllPost() {
    return "this is post data";
  }
}
