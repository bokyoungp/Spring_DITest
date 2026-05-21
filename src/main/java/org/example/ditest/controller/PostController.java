package org.example.ditest.controller;

import lombok.RequiredArgsConstructor;
import org.example.ditest.model.Post;
import org.example.ditest.repository.PostRepository;
import org.example.ditest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
  final PostService service;
  PostRepository repository;

//  public PostController(PostService service){
//    this.service = service;
//  }

//  public void setService(PostService service) {
//    this.service = service;
//  }

  @GetMapping("/posts")
  public List<Post> getAllPost() {
    return service.getAllPost();
  }

  @PostMapping("/posts")
  public Post createNewPost() {
    Post p = new Post(0,"test", "test-body", 0);
    p.setPostId(service.createNewPost(p));
    return p;
  }
}
