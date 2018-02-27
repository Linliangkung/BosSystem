package com.jsako.bos.test.domain;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalProxyFactory<T> implements MethodInterceptor {

	// 被代理对象
	private T target;

	public TransactionalProxyFactory(T target) {
		this.target = target;
	}

	public T newProxyInstance() {
		// 获取被代理对象所有方法
		for (Method method : target.getClass().getMethods()) {
			// 遍历所有方法,判断方法是否有@Transactional注解
			if (method.getAnnotation(Transactional.class) != null) {
				// 有方法有@Transactional注解
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(target.getClass());
				enhancer.setCallback(this);
				return (T) enhancer.create();
			}
		}
		//没有@Transactional注解不用被代理
		return target;
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] objs, MethodProxy arg3) throws Throwable {
		// 判断当前方法是否有@Transactional注解
		if (method.getAnnotation(Transactional.class) == null) {
			// 没有@Transactional注解
			return method.invoke(target, objs);
		}
		// 有@Transactional注解
		System.out.println("beganTransaction");
		Object value = method.invoke(target, objs);
		System.out.println("commit");
		return value;
	}

}
