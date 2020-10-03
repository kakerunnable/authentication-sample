package jp.bootware.authesample.infrastructure.auth.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserSummary {

  private Long userId;
  private String email;
  private String username;
  private Set<String> roles;
}
