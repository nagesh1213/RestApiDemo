package com.spring.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.model.ApiResponseEntity;
import com.spring.security.model.Role;
import com.spring.security.model.User;
import com.spring.security.model.UserRole;
import com.spring.security.repository.ApiResponseRepository;
import com.spring.security.repository.RoleRepository;
import com.spring.security.repository.UserRepository;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ApiResponseRepository apiResponseRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User createUser(User user) throws Exception {

		user.setUsername(user.getUsername());
		String encyptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encyptedPassword);
		Role role = new Role();
		role.setRoleId((long) 1);
		role.setName("ROLE_ADMIN");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));
		for (UserRole ur : userRoles) {
			roleRepository.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		User localUser = userRepository.save(user);
		return localUser;

	}

	@Override
	public Optional<?> findAll() {
		return Optional.of(userRepository.findAll());
	}

	@Override
	public void saveResponse(String response) {
		ApiResponseEntity apiResponseEntity = new ApiResponseEntity();
		apiResponseEntity.setResponse(response);
		apiResponseRepository.save(apiResponseEntity);

	}

}
