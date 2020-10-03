package jp.bootware.authesample.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExtExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handle(IllegalArgumentException exception, HttpStatus httpStatus) {
    return handle(exception, httpStatus);
  }

  public ResponseEntity<ErrorResponse> handle(Throwable exception, HttpStatus httpStatus) {
    return new ResponseEntity<>(handleException(exception), httpStatus);
  }

  ErrorResponse handleException(Throwable exception) {
    log.error("Error!", exception);
    return ErrorResponse.builder().message(exception.getMessage()).build();
  }
}
