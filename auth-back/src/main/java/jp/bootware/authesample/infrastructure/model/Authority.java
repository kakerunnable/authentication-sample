package jp.bootware.authesample.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Authority implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
  @SequenceGenerator(name = "authority_seq", sequenceName = "authority_sequence", allocationSize = 1)
  private Long id;

  @Column(length = 100, unique = true)
  @NotNull
  private String name;

  @JsonIgnore
  @ToString.Exclude
  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<UserInfo> userInfos;

  public GrantedAuthority grantedAuthority() {
    return new SimpleGrantedAuthority(this.name);
  }
}
