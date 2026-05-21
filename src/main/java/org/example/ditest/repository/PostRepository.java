package org.example.ditest.repository;

import org.example.ditest.model.Post;

import java.util.List;

public interface PostRepository {
  List<Post> findAll();
  Post findById(int postId);
  int insertPost(Post post);
  void deletePost(int PostId);
  void updatePost(Post post);
}
