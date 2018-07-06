package com.kgregorczyk.library.repository;

import com.kgregorczyk.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);

}
