package org.example.ditest.dto;

import org.example.ditest.model.Post;

public record PostResponseUserDto(
    int postId,
    String title,
    String body,
    String userId
) {
  // static factory method
  public static PostResponseUserDto of(Post post) {
    return new PostResponseUserDto(
      post.getPostId(),post.getTitle(),post.getBody(), post.getUserId()
    );
  }
}
