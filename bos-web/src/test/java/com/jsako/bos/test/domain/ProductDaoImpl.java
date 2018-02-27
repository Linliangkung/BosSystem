package com.jsako.bos.test.domain;

import org.springframework.transaction.annotation.Transactional;

public class ProductDaoImpl implements IProductDao,IDao{
	
	public void save(){
		System.out.println("save");
	}
	public void say() {
		System.out.println("say");
	}
	
	public void haha(){
		System.out.println("haha");
	}
}
