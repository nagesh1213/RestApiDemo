package com.spring.security.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.model.User;
import com.spring.security.repository.UserRepository;


@Service
public class UserSecurityService implements UserDetailsService {

	final Logger logger = Logger.getLogger(UserSecurityService.class.getName());
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(username);
		User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("Username not found");
		}
		return user;

	}
}
