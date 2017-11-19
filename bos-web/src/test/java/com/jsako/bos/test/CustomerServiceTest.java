package com.jsako.bos.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsako.crmbos.service.Customer;
import com.jsako.crmbos.service.impl.ICustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerServiceTest {
	
	@Autowired
	private ICustomerService customerService;
	
	@Test
	public void test() {
		List<Customer> findListByDecidedzoneId = customerService.findListByDecidedzoneId("11");
		findListByDecidedzoneId.forEach((customer)->{
			System.out.println(customer.getName());
		});
	}

}
