package jp.bootware.authesample.infrastructure.auth.service;

import jp.bootware.authesample.infrastructure.auth.dto.CustomUserDetails;
import jp.bootware.authesample.infrastructure.model.UserInfo;
import jp.bootware.authesample.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    UserInfo userInfo = userRepository.findByEmailOrUsername(s);
    return new CustomUserDetails(userInfo);
  }
}
