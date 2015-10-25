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
import com.mediabox.findpro.data.Payment;

@Repository("paymentDao")
public class PaymentDao {
	private static final Log log = LogFactory.getLog(PaymentDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public PaymentDao() {
	}
	
	public PaymentDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Payment> getPaymentByUserId(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Payment> paymentList = session.createCriteria(Payment.class).add(Restrictions.eq("userId", userId)).list();
		return paymentList;
	}
	
	public Payment getPaymentByIdAndUserId(int id, int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Payment payment = (Payment)session.createCriteria(Payment.class).add(Restrictions.eq("idpayment", id)).add(Restrictions.eq("userId", userId)).uniqueResult();
		return payment;
	}
	
	public Payment getPaymentById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Payment payment = (Payment)session.createCriteria(Payment.class).add(Restrictions.eq("idpayment", id)).uniqueResult();
		return payment;
	}
	
	public Payment getDefaultPayment(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Payment payment = (Payment)session.createCriteria(Payment.class).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("isPrimary", true)).uniqueResult();
		return payment;
	}
	
	public boolean deletePayment(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Payment payment = this.getPaymentById(id);
		session.delete(payment);
		return true;
	}
	
	public void persist(Payment instance) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
}
