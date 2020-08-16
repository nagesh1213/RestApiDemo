package com.rest.api.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.service.TestServiceInter;

@RestController
public class HomeController {
	
	private static final AtomicInteger counter = new AtomicInteger();
	
	@Autowired
	private TestServiceInter testServiceInter;
	
	@PostMapping("/counter")
	public ResponseEntity<?> counter(){
		return ResponseEntity.ok().body(testServiceInter.testCount(counter.incrementAndGet()));
	}
}
