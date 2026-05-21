package org.example.ditest.controller;

import org.example.ditest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
  PostService service;

  @Autowired
  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping("/post")
  public String getAllPost() {
    return service.getAllPost();
  }
}
