package org.example.ditest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ditest.dto.PostRequestDto;
import org.example.ditest.dto.PostResponseDto;
import org.example.ditest.dto.PostResponseDto2;
import org.example.ditest.model.Post;
import org.example.ditest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
  final PostRepository repository;

//  public PostService(PostRepository repository) {
//    this.repository = repository;
//  }

  public List<PostResponseDto> getAllPost() {

    return repository.findAll()
        .stream()
        .map(PostResponseDto::of)
        .toList();
  }

  public PostResponseDto createNewPost(PostRequestDto reqDto) {
    // refactoring 대상
    Post post = new Post(0,reqDto.title(), reqDto.body(), 0);
    post.setPostId(repository.insertPost(post));
//    log.info("service post -- {}", post);
    return PostResponseDto.of(post);
  }

  public PostResponseDto updatePost(int postId) {
    // likes 만 증가하는 update 가 biz.logic 임
//    log.info(" post id before update - {}", postId);
    Post getPost = repository.findById(postId);
//    log.info(" post before update - {}", getPost);

  // 수정하기
    getPost.setLikes(getPost.getLikes() + 1);

    repository.updatePost(getPost);
//    log.info(" post after update - {}", getPost);
    return PostResponseDto.of(getPost);
  }

  public void deletePost(int postId) {
    repository.deletePost(postId);
  }

  public PostResponseDto getOnePost(int postId) {
//    log.info(" post id getOnePost - {}", postId);
    Post post = repository.findById(postId);
//    log.info(" post of getOnePost - {}", post);
    return PostResponseDto.of(post);
  }

  public PostResponseDto2 updatePostLikes(int postId) {
    // likes 만 증가하는 update 가 biz.logic 임
    Post post = repository.findById(postId);
    // 수정하기
    post.setLikes(post.getLikes() + 1);
    repository.updatePost(post);

    return PostResponseDto2.of(post);
  }

//  public PostResponseDto2 updatePost2(int postId) {
//    // likes 만 증가하는 update 가 biz.logic 임
//    Post post = repository.findById(postId);
//    // 수정하기
//    post.setLikes(post.getLikes() + 1);
//
//    repository.updatePost(post);
//    return PostResponseDto2.of(post);
//  }
}
