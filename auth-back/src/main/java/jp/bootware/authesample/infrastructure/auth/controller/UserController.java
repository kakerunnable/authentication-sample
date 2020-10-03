package jp.bootware.authesample.infrastructure.auth.controller;

import jp.bootware.authesample.infrastructure.auth.dto.UserSummary;
import jp.bootware.authesample.infrastructure.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserSummary> me() {
    return ResponseEntity.ok(userService.getUserProfile());
  }
}
