package com.jsako.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	private T model;

	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz=(Class) genericSuperclass.getActualTypeArguments()[0];
		try {
			model=(T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public T getModel() {
		return model;
	}
	
}
