package com.rest.api.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TestDaoImpl implements TestDaoInter {

	@Autowired
	private SessionFactory sessionFactory;


	public String testCount(int i) {
		String qry = "update TestEntity t set t.count= :counter where t.id = :id";
		try{
			sessionFactory
			.getCurrentSession().createQuery(qry).setParameter("counter", i).setParameter("id", 1L).executeUpdate();
			return "success";
		}catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}
	
}
