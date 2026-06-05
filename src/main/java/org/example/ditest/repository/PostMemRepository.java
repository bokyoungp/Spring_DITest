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

  public PostMemRepository() {
    Post post = new Post(++seq, "test1", "body1", 0);
    posts.put(seq, post);
    post = new Post(++seq, "test2", "body2", 0);
    posts.put(seq, post);
  }

  @Override
  public List<Post> findAll() {
    return new ArrayList<>(posts.values());
  }

  @Override
  public Post findById(int postId) {
    return posts.get(postId);
  }

  @Override
  public int insertPost(Post post) {
    post.setPostId(++seq);
    posts.put(seq, post);
    return post.getPostId();
  }

  @Override
  public void deletePost(int postId) {
    posts.remove(postId);
  }

  @Override
  public void updatePost(Post post) {

    posts.put(post.getPostId(), post);
  }
}
