package org.example.ditest.controller;

import lombok.RequiredArgsConstructor;
import org.example.ditest.dto.PostRequestDto;
import org.example.ditest.dto.PostResponseDto;
import org.example.ditest.model.Post;
import org.example.ditest.repository.PostRepository;
import org.example.ditest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
  final PostService service;
  PostRepository repository;

  @GetMapping("/posts")
  public List<Post> getAllPost() {
    return service.getAllPost();
  }

  @PostMapping("/posts")
  public PostResponseDto createNewPost(@RequestBody PostRequestDto reqDto) {
  // refactoring 대상
    //Post p = new Post(0,reqDto.title(), reqDto.body(), 0);
    return service.createNewPost(reqDto);
  }
}
