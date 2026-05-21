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

  public List<Post> getAllPost() {
    return repository.findAll();
  }

  public PostResponseDto createNewPost(PostRequestDto reqDto) {
    // refactoring 대상
    Post post = new Post(0,reqDto.title(), reqDto.body(), 0);
    post.setPostId(repository.insertPost(post));

    return PostResponseDto.of(post);
  }
}
