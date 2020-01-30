package com.kgregorczyk.library.repository;

import com.kgregorczyk.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
}
