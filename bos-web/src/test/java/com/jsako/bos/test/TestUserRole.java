package com.jsako.bos.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsako.bos.domain.Role;
import com.jsako.bos.domain.User;
import com.jsako.bos.utils.MD5Utils;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class TestUserRole {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void test() {
		Session openSession = sessionFactory.openSession();
		Transaction beginTransaction = openSession.beginTransaction();
		
		User user=openSession.get(User.class,2);
		
		User user2=new User();
		user2.setUsername("linliangkung");
		user2.setPassword(MD5Utils.md5("123"));
		Role role1=new Role();
		role1.setId("1");
		role1.setDescription("角色1");
		
		Role role2=new Role();
		role2.setId("2");
		role2.setDescription("角色2");
		
		Role role3=new Role();
		role3.setId("3");
		role3.setDescription("角色3");
		
		user.getRoles().add(role1);
		user.getRoles().add(role2);
		user.getRoles().add(role3);
		
		user2.getRoles().add(role1);
		user2.getRoles().add(role3);
		
		role1.getUsers().add(user);
		role1.getUsers().add(user2);
		role2.getUsers().add(user);
		role3.getUsers().add(user);
		role3.getUsers().add(user2);
		
		openSession.save(role1);
		openSession.save(role2);
		openSession.save(role3);
		openSession.save(user2);
		
		beginTransaction.commit();
		openSession.close();
		sessionFactory.close();
	}
	
	@Test
	public void test2(){
		System.out.println(MD5Utils.md5("admin"));
	}
}
