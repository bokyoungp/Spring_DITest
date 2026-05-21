package org.example.ditest.service;

import lombok.RequiredArgsConstructor;
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

  public int createNewPost(Post post) {
    return repository.insertPost(post);
  }
}
