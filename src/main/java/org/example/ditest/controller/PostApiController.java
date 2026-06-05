package org.example.ditest.controller;

import lombok.RequiredArgsConstructor;
import org.example.ditest.dto.PostRequestDto;
import org.example.ditest.dto.PostResponseDto;
import org.example.ditest.dto.PostResponseDto2;
import org.example.ditest.model.Post;
import org.example.ditest.repository.PostRepository;
import org.example.ditest.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostApiController {
  final PostService service;
  PostRepository repository;

  @GetMapping("/posts")
  public List<PostResponseDto> getAllPost() {
    return service.getAllPost();
  }

  @PostMapping("/posts")
  public PostResponseDto createNewPost(@RequestBody PostRequestDto reqDto) {
  // refactoring 대상
    //Post p = new Post(0,reqDto.title(), reqDto.body(), 0);
    return service.createNewPost(reqDto);
  }

  @PutMapping("/posts/{postId}")
  public PostResponseDto2 updatePostLikes(@PathVariable int postId) {
    return service.updatePostLikes(postId);
  }

//  @GetMapping("/posts/{postId}")
//  public ResponseEntity<PostResponseDto> getOnePost(@PathVariable int postId) {
//    PostResponseDto postDto = service.getOnePost(postId);
//    return ResponseEntity.ok().eTag("\""+ postDto.hashCode() +"\"")
//        .body(postDto);
//  }

//  @PutMapping("/posts/{postId}")
//  public PostResponseDto2 updatePost2(@PathVariable("postId") int postId) {
//    return service.updatePost2(postId);
//  }
}
