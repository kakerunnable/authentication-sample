package jp.bootware.authesample.infrastructure.service;

import java.time.LocalDateTime;
import jp.bootware.authesample.infrastructure.dto.Token;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:39, Pzt
 **/
public interface TokenProvider {

  Token generateAccessToken(String subject);

  Token generateRefreshToken(String subject);

  String getUsernameFromToken(String token);

  LocalDateTime getExpiryDateFromToken(String token);

  boolean validateToken(String token);
}
