package com.jsako.bos.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsako.bos.domain.Function;
import com.jsako.bos.domain.Role;
import com.jsako.bos.domain.User;
import com.jsako.bos.utils.MD5Utils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestFunctionRole {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void test() {
		Session openSession = sessionFactory.openSession();
		Transaction beginTransaction = openSession.beginTransaction();
		Role role1=openSession.get(Role.class, "1");
		Role role2=openSession.get(Role.class, "2");
		Role role3=openSession.get(Role.class, "3");
		
		Function function1=new Function();
		function1.setId("1");
		Function function2=new Function();
		function2.setId("2");
		Function function3=new Function();
		function3.setId("3");
		
		//1有1 2权限
		role1.getFunctions().add(function1);
		role1.getFunctions().add(function2);
		function1.getRoles().add(role1);
		function2.getRoles().add(role1);
		
		//2有2 权限
		role2.getFunctions().add(function2);
		function2.getRoles().add(role2);
		//3有3 权限
		role3.getFunctions().add(function3);
		function3.getRoles().add(role3);
		
		openSession.save(function1);
		openSession.save(function2);
		openSession.save(function3);
		
		beginTransaction.commit();
		openSession.close();
		sessionFactory.close();
	}
	
}
