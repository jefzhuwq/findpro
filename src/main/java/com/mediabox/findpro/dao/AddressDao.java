package com.mediabox.findpro.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediabox.findpro.data.AddressBook;
import com.mediabox.findpro.data.Menu;

@Repository("addressDao")
public class AddressDao {
	private static final Log log = LogFactory.getLog(AddressDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AddressDao() {
	}
	
	public AddressDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<AddressBook> getAddressByUserId(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<AddressBook> addressList = session.createCriteria(AddressBook.class).add(Restrictions.eq("userId", userId)).list();
		return addressList;
	}
	
	public AddressBook getDefaultAddress(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		AddressBook address = (AddressBook)session.createCriteria(AddressBook.class).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("isPrimary", true)).uniqueResult();
		return address;
	}
	
	public Menu getMenuById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Menu menu = (Menu)session.createCriteria(Menu.class).add(Restrictions.eq("idmenu", id)).uniqueResult();
		return menu;
	}
}
