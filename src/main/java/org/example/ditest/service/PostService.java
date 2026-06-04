package org.example.ditest.service;

import lombok.RequiredArgsConstructor;
import org.example.ditest.dto.PostRequestDto;
import org.example.ditest.dto.PostResponseDto;
import org.example.ditest.model.Post;
import org.example.ditest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    return PostResponseDto.of(post);
  }

  public PostResponseDto updatePost(int postId) {
    // likes 만 증가하는 update 가 biz.logic 임
    Post getPost = repository.findById(postId);
    int likes = getPost.getLikes();
    likes++;
    repository.updatePost(getPost);
    return PostResponseDto.of(getPost);
  }

  public void deletePost(int postId) {
    repository.deletePost(postId);
  }

  public PostResponseDto getOnePost(int postId) {
    Post post = repository.findById(postId);
    return PostResponseDto.of(post);
  }
}
