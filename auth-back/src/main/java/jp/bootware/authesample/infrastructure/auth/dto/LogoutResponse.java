package jp.bootware.authesample.infrastructure.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogoutResponse {

  private LogoutResponse.SuccessFailure status;
  private String message;

  public enum SuccessFailure {
    SUCCESS, FAILURE
  }
}
