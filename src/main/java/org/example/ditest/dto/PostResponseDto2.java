package org.example.ditest.dto;

import org.example.ditest.model.Post;

public record PostResponseDto2(
    int postId,
    String title,
    int likes
) {
  // static factory method
  public static PostResponseDto2 of(Post post) {
    return new PostResponseDto2(
      post.getPostId(),post.getTitle(),post.getLikes()
    );
  }
}
