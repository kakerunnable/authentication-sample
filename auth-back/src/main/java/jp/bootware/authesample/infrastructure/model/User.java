package jp.bootware.authesample.infrastructure.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import jp.bootware.authesample.infrastructure.dto.UserSummary;
import lombok.Data;

@Entity
@Data
public class User implements Serializable {

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
    return userSummary;
  }
}
