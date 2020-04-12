package com.caroline.fruit.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.TemporalType.DATE;

import com.caroline.fruit.audit.CreateAndLastModifiedDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles"})
@EqualsAndHashCode(exclude = {"roles"}, callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "t_user", indexes = {@Index(columnList = "username", unique = true)})
public class User extends CreateAndLastModifiedDate implements UserDetails, Serializable {

  private static final long serialVersionUID = 1881229773610861294L;

  @Id
  private Long id;

  @NotEmpty
  @NotBlank
  @NotNull
  //姓名
  private String realName;

  //生日
  @Temporal(DATE)
  private Date birthDay;

  //加入日期
  @Temporal(DATE)
  private Date joinDay;

  //登录者的用户名(仅限可登录用户-管理员)
  private String username;

  private String password;

  //微信账号
  private String weChatAccount;

  //性别
  private String gender;

  //微信昵称
  private String weChatName;

  //积分
  private Integer integral;

  //备注
  private String userRemark;

  private String accessToken;

  @NotNull
  private boolean isEnabled = true;

  @JsonBackReference
  @ManyToMany(cascade = {CascadeType.ALL}, fetch = EAGER)
  @JoinTable(name = "blog_user_role", joinColumns = @JoinColumn(name = "blog_user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;


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
    return this.username;
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