package com.spring.security.service;


import java.util.Optional;

import com.spring.security.model.User;


public interface UserService {

	User findByUsername(String username);

	User createUser(User user) throws Exception;

	Optional<?> findAll();

	void saveResponse(String string);

}
