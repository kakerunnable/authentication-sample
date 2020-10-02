package jp.bootware.authesample.infrastructure.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:39, Pzt
 **/
@Data
@AllArgsConstructor
public class Token {

  private TokenType tokenType;
  private String tokenValue;
  private Long duration;
  private LocalDateTime expiryDate;

  public enum TokenType {
    ACCESS, REFRESH
  }
}
