package jp.bootware.authesample.infrastructure.repository;

import java.util.Optional;
import jp.bootware.authesample.infrastructure.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:04, Pzt
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);
}
