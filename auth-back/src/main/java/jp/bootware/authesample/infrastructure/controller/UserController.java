package jp.bootware.authesample.infrastructure.controller;

import jp.bootware.authesample.infrastructure.dto.UserSummary;
import jp.bootware.authesample.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:11:42, Pzt
 **/
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
