package com.spring.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.security.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

}
