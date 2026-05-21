package org.example.ditest.repository;

import org.example.ditest.model.Post;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostMemRepository implements PostRepository{
  private static Map<Integer, Post> posts = new HashMap<>();
  private static int seq = 0;

  @Override
  public List<Post> findAll() {
    return new ArrayList<>(posts.values());
  }

  @Override
  public Post findById(int postId) {
    return null;
  }

  @Override
  public int insertPost(Post post) {
    post.setPostId(++seq);
    posts.put(seq, post);
    return post.getPostId();
  }

  @Override
  public void deletePost(int PostId) {

  }

  @Override
  public void updatePost(Post post) {

  }
}
