package org.example.ditest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ditest.dto.PostRequestDto;
import org.example.ditest.dto.PostResponseDto;
import org.example.ditest.model.Post;
import org.example.ditest.repository.PostRepository;
import org.example.ditest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
  final PostService service;

  @GetMapping("/posts/all")
  public String getAllPost(Model model) {
    model.addAttribute("postList", service.getAllPost());
    log.info("get-mapping : getAllPost");
    return "postAll";
  }

  @GetMapping("/posts/new")
  public String createNewPost() {
    log.info("get-mapping : createNewPost");
    return "postNew";
  }

  @PostMapping("/posts/new")
  public String createNewPost(@ModelAttribute PostRequestDto reqDto){

    log.info("{}", reqDto);
    service.createNewPost(reqDto);

    return "redirect:/posts/all";
  }

  @GetMapping("/posts/update/{postId}")
  public String updatePost(@PathVariable("postId") int postId, Model model) {
    log.info("get-mapping : updatePost : postId -- {} ", postId);
    PostResponseDto onePost = service.getOnePost(postId);
    log.info("get-mapping : updatePost : findPost -- {} ", onePost);
    model.addAttribute("post", service.getOnePost(postId));
    return "postUpdate";
  }

  @PostMapping("/posts/update/{postId}")
  public String updatePost(@PathVariable("postId") int postId) {
    service.updatePost(postId);
    //PRG 패턴으로 리팩토링
    return "redirect:/posts/all";
  }
}
