package jp.bootware.authesample.infrastructure.auth.service;

import jp.bootware.authesample.infrastructure.auth.dto.LoginRequest;
import jp.bootware.authesample.infrastructure.auth.dto.LoginResponse;
import jp.bootware.authesample.infrastructure.auth.dto.LogoutResponse;
import jp.bootware.authesample.infrastructure.auth.dto.UserSummary;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken,
                                      String refreshToken);

  ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken);

  ResponseEntity<LogoutResponse> logout();

  UserSummary getUserProfile();
}
