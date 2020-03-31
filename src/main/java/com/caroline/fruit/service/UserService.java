package com.caroline.fruit.service;

import com.caroline.fruit.dto.AddUserDto;
import com.caroline.fruit.exception.FruitException;
import com.caroline.fruit.message.Result;
import com.caroline.fruit.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  User createBasicUser(User user);

  User findByUsername(String email);

  Result findByWeChatAccount(String weChatAccount) throws FruitException;

  User getLoggedInUser();

  Result addUser(AddUserDto addUserDto) throws FruitException;

  Result getUserList(Pageable pageable) throws  FruitException;

  boolean isAuthenticated();

  void autoLogin(String email, String password);
}