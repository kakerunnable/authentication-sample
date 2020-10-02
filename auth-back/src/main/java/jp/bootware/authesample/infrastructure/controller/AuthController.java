package jp.bootware.authesample.infrastructure.controller;

import javax.validation.Valid;
import jp.bootware.authesample.infrastructure.dto.LoginRequest;
import jp.bootware.authesample.infrastructure.dto.LoginResponse;
import jp.bootware.authesample.infrastructure.service.UserService;
import jp.bootware.authesample.infrastructure.util.SecurityCipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:52, Pzt
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LoginResponse> login(
      @CookieValue(name = "accessToken", required = false) String accessToken,
      @CookieValue(name = "refreshToken", required = false) String refreshToken,
      @Valid @RequestBody LoginRequest loginRequest
  ) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
            loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
    String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
    return userService.login(loginRequest, decryptedAccessToken, decryptedRefreshToken);
  }

  @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LoginResponse> refreshToken(
      @CookieValue(name = "accessToken", required = false) String accessToken,
      @CookieValue(name = "refreshToken", required = false) String refreshToken) {
    String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
    String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
    return userService.refresh(decryptedAccessToken, decryptedRefreshToken);
  }
}
