package com.kgregorczyk.library.service;

import com.kgregorczyk.library.model.Role;
import com.kgregorczyk.library.model.User;
import com.kgregorczyk.library.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private RoleService roleService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private AuthenticationManager authenticationManager;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, RoleService roleService,
      @Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
      @Lazy AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.roleService = roleService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public User createBasicUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    Set<Role> roles = new HashSet<>();
    roles.add(roleService.getRole("USER"));
    user.setRoles(roles);
    return userRepository.save(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User getLoggedInUser() {
    Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (user instanceof User) {
      return (User) user;
    }
    return null;
  }

  @Override
  public boolean isAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
        && authentication.isAuthenticated();

  }

  @Override
  public void autoLogin(String email, String password) {
    UserDetails userDetails = this.findByEmail(email);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, password,
            userDetails.getAuthorities());

    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = this.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException(
          String.format("User with email %s was not found!", email));
    }
    return user;
  }
}