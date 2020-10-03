package jp.bootware.authesample.infrastructure.repository;

import jp.bootware.authesample.infrastructure.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

  Optional<UserInfo> findByEmail(String email);

  Optional<UserInfo> findByUsername(String username);

  default UserInfo findByEmailOrUsername(String emailOrUsername) {
    Optional<UserInfo> userInfoOpt = Optional.empty();

    if(emailOrUsername.matches(".+@.+")) {
      userInfoOpt = findByEmail(emailOrUsername);
    } else {
      userInfoOpt = findByUsername(emailOrUsername);
    }

    UserInfo userInfo = userInfoOpt
        .orElseThrow(() -> new UsernameNotFoundException("User not found with userId " + emailOrUsername));

    return userInfo;
  }
}
