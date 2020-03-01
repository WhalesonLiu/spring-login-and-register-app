package com.kgregorczyk.library.service;

import com.kgregorczyk.library.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User createBasicUser(User user);

  User findByUsername(String email);

  User getLoggedInUser();

  boolean isAuthenticated();

  void autoLogin(String email, String password);
}