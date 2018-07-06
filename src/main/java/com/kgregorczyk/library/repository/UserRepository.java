package com.kgregorczyk.library.repository;

import com.kgregorczyk.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
}
