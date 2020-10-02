package jp.bootware.authesample.infrastructure.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:50, Pzt
 **/
@Data
public class LoginRequest {

  @NotBlank(message = "Email address cannot be empty")
  @Email(message = "Please provide valid email address")
  private String email;

  @NotBlank(message = "Password cannot be empty")
  private String password;
}
