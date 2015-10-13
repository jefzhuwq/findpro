package com.mediabox.findpro.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediabox.findpro.data.Menu;

@Repository("menuDao")
public class MenuDao {
	private static final Log log = LogFactory.getLog(MenuDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public MenuDao() {
	}
	
	public MenuDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Menu> getMenuByCategoryId(int categoryId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Menu> menuList = session.createCriteria(Menu.class).add(Restrictions.eq("categoryId", categoryId)).list();
		return menuList;
	}
}
