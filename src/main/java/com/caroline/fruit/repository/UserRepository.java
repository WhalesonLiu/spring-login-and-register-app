package com.caroline.fruit.repository;

import com.caroline.fruit.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String email);

  Page<User> findAll(Pageable pageable);

  User findByWeChatAccount(String weChatAccount);
}
