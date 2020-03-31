package com.caroline.fruit.service;

import com.caroline.fruit.model.Role;
import com.caroline.fruit.repository.RoleRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RoleServiceImpl implements RoleService {

  private static final Map<String, Role> ROLE_MAP = new ConcurrentHashMap<>();
  private RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Role getRole(String name) {
    return ROLE_MAP.computeIfAbsent(name, this::getOrCreateRole);
  }

  @Transactional
  protected Role getOrCreateRole(String name) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      return createRole(name);
    }
    return role;
  }

  private Role createRole(String name) {
    Role role = new Role();
    role.setName(name);
    return roleRepository.save(role);
  }
}
