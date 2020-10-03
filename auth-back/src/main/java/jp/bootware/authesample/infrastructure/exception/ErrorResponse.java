package jp.bootware.authesample.infrastructure.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

  private String message;
}
