package com.kgregorczyk.library.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kgregorczyk.library.audit.CreateAndLastModifiedDate;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles", "articles"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends CreateAndLastModifiedDate implements UserDetails, Serializable {

  private static final long serialVersionUID = 1881229773610861294L;

  @Id
  @GeneratedValue
  private Long id;

  @NotEmpty
  @NotBlank
  @Email
  @NotNull
  private String email;

  @NotBlank
  @NotEmpty
  @NotNull
  private String password;

  @NotNull
  private boolean isEnabled = true;

  @JsonBackReference
  @ManyToMany(cascade = {CascadeType.ALL}, fetch = EAGER)
  @JoinTable(name = "blog_user_role", joinColumns = @JoinColumn(name = "blog_user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", fetch = LAZY)
  @JsonBackReference
  private Set<Article> articles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : this.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getName())));
    }
    return grantedAuthorities;
  }

  @Override
  public String getUsername() {
    return this.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.isEnabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

}

