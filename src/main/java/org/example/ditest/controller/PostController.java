package org.example.ditest.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ditest.dto.PostRequestDto;
import org.example.ditest.dto.PostResponseDto;
import org.example.ditest.dto.PostResponseUserDto;
import org.example.ditest.model.Post;
import org.example.ditest.repository.PostRepository;
import org.example.ditest.service.PostService;
import org.example.ditest.session.SessionUtils;
import org.example.ditest.session.UserInfo;
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
  public String getAllPostLikes(Model model) {
    model.addAttribute("postList", service.getAllPostLikes());
    return "postAll";
  }

  @GetMapping("/posts/new")
  public String createNewPost() {

//    log.info("get-mapping : createNewPost");
    return "postNew";
  }

  @PostMapping("/posts/new")
  public String createNewPost(@ModelAttribute PostRequestDto reqDto,
                              HttpServletRequest req){
    UserInfo userInfo = SessionUtils.getUserInfoFromCookie(req);
    if (userInfo == null) {
      return "redirect:/login";
    }
    service.createNewPost(reqDto);
    return "redirect:/posts/all";
  }

  @GetMapping("/posts/update/{postId}")
  public String updatePost(@PathVariable("postId") int postId, Model model) {
//    log.info("get-mapping : updatePost : postId -- {} ", postId);
    PostResponseUserDto onePost = service.getOnePostUser(postId);
//    log.info("get-mapping : updatePost : findPost -- {} ", onePost);
    model.addAttribute("post", onePost);
    return "postUpdate";
  }

  @PostMapping("/posts/update/{postId}")
  public String updatePost(@PathVariable("postId") int postId,
                           HttpServletRequest req) {
    UserInfo userInfo = SessionUtils.getUserInfoFromCookie(req);
    if (userInfo != null) {
     // 로그인 한 사용자만 좋아요 를 증가시킬 수 있음, 본인의 글 제외
      service.updatePostUser(postId, userInfo.getUserId());
    }
    //PRG 패턴으로 리팩토링
    return "redirect:/posts/all";
  }

  @PostMapping("/posts/delete/{postId}")
  public String deletePost(@PathVariable("postId") int postId,
                           HttpServletRequest req) {
    UserInfo userInfo = SessionUtils.getUserInfoFromCookie(req);
    if (userInfo != null) { // 로그인 한 사용자만 본인이 작성한 글에 한하여 삭제 가능
      service.deletePostUser(postId, userInfo.getUserId());
    }
    //PRG 패턴으로 리팩토링
    return "redirect:/posts/all";
  }
}
