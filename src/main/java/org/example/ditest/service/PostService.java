package org.example.ditest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ditest.dto.*;
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

  public List<PostResponseDto> getAllPostUser() {

    return repository.findAll()
        .stream()
        .map(PostResponseDto::of)
        .toList();
  }

  public PostResponseDto createNewPost(PostRequestDto reqDto) {
    // refactoring 대상
    Post post = new Post(0, reqDto.title(), reqDto.body(), 0, "aaa");
    post.setPostId(repository.insertPost(post));
//    log.info("service post -- {}", post);
    return PostResponseDto.of(post);
  }

  public PostResponseUserDto createNewPostUser(PostRequestUserDto reqDto) {
    Post post = new Post(0, reqDto.title(), reqDto.body(), 0, reqDto.userId());
    post.setPostId(repository.insertPost(post));
    return PostResponseUserDto.of(post);
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

  public PostResponseDto updatePostUser(int postId, String userId) {
    // likes 만 증가하는 update 가 biz.logic 임, 본인이 쓴 글에 like 는 추가할 수 없음

    Post getPost = repository.findById(postId);
    if (!getPost.getUserId().equals(userId)) {
      // 수정하기
      getPost.setLikes(getPost.getLikes() + 1);
      repository.updatePost(getPost);
    }
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

  public PostResponseUserDto getOnePostUser(int postId) {
    Post post = repository.findById(postId);
    return PostResponseUserDto.of(post);
  }

  public PostResponseDto2 updatePostLikes(int postId) {
    // likes 만 증가하는 update 가 biz.logic 임
    Post post = repository.findById(postId);
    // 수정하기
    post.setLikes(post.getLikes() + 1);
    repository.updatePost(post);

    return PostResponseDto2.of(post);
  }

  public void deletePostUser(int postId, String userId) {
    // 자신이 작성한 글인 경우에만 삭제 가능
    Post post = repository.findById(postId);
    if (post.getUserId().equals(userId)) {
      log.info("동일한 사용자가 작성한 게시물이므로 삭제합니다.");
      repository.deletePost(postId);
    }
  }

  public List<PostResponseDto2> getAllPostLikes() {
      return repository.findAll()
          .stream()
          .map(PostResponseDto2::of)
          .toList();
    }
}


