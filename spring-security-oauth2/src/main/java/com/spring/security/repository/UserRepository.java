package com.spring.security.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.spring.security.model.User;


@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
