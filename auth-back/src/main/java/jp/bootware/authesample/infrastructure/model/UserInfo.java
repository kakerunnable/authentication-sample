package jp.bootware.authesample.infrastructure.model;

import jp.bootware.authesample.infrastructure.auth.dto.UserSummary;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class UserInfo implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
  private Long id;

  @Column(length = 50, unique = true)
  @NotNull
  private String username;

  @Column(length = 100, unique = true)
  @NotNull
  @Email
  private String email;

  @Column(length = 200)
  private String password;

  @NotNull
  private Boolean enabled;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthProvider provider;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")},
      inverseJoinColumns = {
          @JoinColumn(name = "authorityId", referencedColumnName = "id")})
  private Set<Authority> authorities;

  public UserSummary toUserSummary() {
    UserSummary userSummary = new UserSummary();
    userSummary.setEmail(this.email);
    userSummary.setUserId(this.id);
    userSummary.setUsername(this.username);

    Set<String> authorityStrings = this.authorities.stream()
        .map(Authority::grantedAuthority)
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toSet());
    userSummary.setRoles(authorityStrings);
    return userSummary;
  }
}
