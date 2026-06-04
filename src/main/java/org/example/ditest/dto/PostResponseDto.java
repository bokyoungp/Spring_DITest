package org.example.ditest.dto;

import org.example.ditest.model.Post;

public record PostResponseDto(
    int postId,
    String title,
    String body
) {
  // static factory method
  public static PostResponseDto of(Post post) {
    return new PostResponseDto(
      post.getPostId(),post.getTitle(),post.getBody()
    );
  }
}
