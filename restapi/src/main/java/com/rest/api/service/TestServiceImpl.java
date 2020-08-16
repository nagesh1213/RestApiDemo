package com.rest.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.dao.TestDaoInter;

@Service
public class TestServiceImpl implements TestServiceInter{
	
	@Autowired
	private TestDaoInter testDaoInter;
	

	public String testCount(int i) {
		return testDaoInter.testCount(i);
	}

}
