package com.jsako.bos.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsako.bos.domain.Workordermanage;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class WorkordermanageDaoTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void test() {
		Session openSession = sessionFactory.openSession();
		Transaction beginTransaction = openSession.beginTransaction();
		Workordermanage workordermanage = new Workordermanage();
		workordermanage.setId("1");
		workordermanage.setProduct("送噶");
		openSession.saveOrUpdate(workordermanage);
		beginTransaction.commit();
		openSession.close();
		sessionFactory.close();
	}
}
