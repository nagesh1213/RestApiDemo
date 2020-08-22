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


	public String testCount() {
		String qry = "update testentity te set te.count = te.count+1 where te.id = :id";
		try{
			sessionFactory.getCurrentSession().createSQLQuery(qry).setParameter("id", 1L).executeUpdate();
			return "success";
		}catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}
	
}
