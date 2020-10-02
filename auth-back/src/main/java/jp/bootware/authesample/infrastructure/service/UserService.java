package jp.bootware.authesample.infrastructure.service;

import jp.bootware.authesample.infrastructure.dto.LoginRequest;
import jp.bootware.authesample.infrastructure.dto.LoginResponse;
import jp.bootware.authesample.infrastructure.dto.UserSummary;
import org.springframework.http.ResponseEntity;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:54, Pzt
 **/
public interface UserService {

  ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken,
      String refreshToken);

  ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken);

  UserSummary getUserProfile();
}
