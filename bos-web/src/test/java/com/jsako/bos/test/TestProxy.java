package com.jsako.bos.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.jsako.bos.test.domain.ProductDaoImpl;
import com.jsako.bos.test.domain.TransactionalProxyFactory;

public class TestProxy {

	@Test
	public void testJDKProxy() throws Exception {
		ProductDaoImpl target = new ProductDaoImpl();
		Object object=  Proxy.newProxyInstance(ProductDaoImpl.class.getClassLoader(),
				ProductDaoImpl.class.getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							System.out.println("before save");
							Object value = method.invoke(target, args);
							System.out.println("after save");
							return value;
					}
				});
		object.getClass().getMethod("haha").invoke(object);
	}
	
	
	@Test
	public void testCglibProxy(){
		ProductDaoImpl target = new ProductDaoImpl();
		ProductDaoImpl proxy = new TransactionalProxyFactory<ProductDaoImpl>(target).newProxyInstance();
		System.out.println(proxy.getClass().getSimpleName());
		proxy.save();
	}
	
}
