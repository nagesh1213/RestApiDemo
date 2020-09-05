package com.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.model.ApiResponseEntity;

public interface ApiResponseRepository extends JpaRepository<ApiResponseEntity, Long> {
	
	

}
