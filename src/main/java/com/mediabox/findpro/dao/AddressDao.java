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
	
	public AddressBook getAddressByIdAndUserId(int id, int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		AddressBook address = (AddressBook)session.createCriteria(AddressBook.class).add(Restrictions.eq("idaddressBook", id)).add(Restrictions.eq("userId", userId)).uniqueResult();
		return address;
	}
	
	public AddressBook getAddressById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		AddressBook address = (AddressBook)session.createCriteria(AddressBook.class).add(Restrictions.eq("idaddressBook", id)).uniqueResult();
		return address;
	}
	
	public AddressBook getDefaultAddress(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		AddressBook address = (AddressBook)session.createCriteria(AddressBook.class).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("isPrimary", true)).uniqueResult();
		return address;
	}
	
	public boolean deleteAddress(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		AddressBook address = this.getAddressById(id);
		session.delete(address);
		return true;
	}
	
	public void persist(AddressBook instance) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
}
