package jp.bootware.authesample.infrastructure.auth.dto;

import jp.bootware.authesample.infrastructure.model.Authority;
import jp.bootware.authesample.infrastructure.model.UserInfo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class CustomUserDetails implements UserDetails {

  private final UserInfo userInfo;

  public CustomUserDetails(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return userInfo.getAuthorities().stream().map(Authority::grantedAuthority)
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return userInfo.getPassword();
  }

  @Override
  public String getUsername() {
    return userInfo.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return userInfo.getEnabled();
  }
}
