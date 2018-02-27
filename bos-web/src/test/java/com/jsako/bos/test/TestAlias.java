package com.jsako.bos.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsako.bos.domain.Subarea;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestAlias {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void test(){
		Session session = sessionFactory.openSession();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.createAlias("region", "r");
		detachedCriteria.add(Restrictions.eq("r.province", "山西省"));
		Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
		List<Subarea> list = executableCriteria.list();
		for (Subarea object : list) {
			System.out.println(object.getAddresskey());
		}
	}
}
