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
import com.mediabox.findpro.data.Order;
import com.mediabox.findpro.data.OrderItem;

@Repository("orderDao")
public class OrderDao {
	private static final Log log = LogFactory.getLog(OrderDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public OrderDao() {
	}
	
	public OrderDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<OrderItem> getOrderItemByOrderId(int orderId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OrderItem> orderItemList = session.createCriteria(OrderItem.class).add(Restrictions.eq("orderId", orderId)).list();
		return orderItemList;
	}
	
	public List<Order> getOrdersByUserId(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session.createCriteria(Order.class).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("isPrimary", true)).list();
		return orderList;
	}
	
	public Order getOrderById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Order order = (Order)session.createCriteria(Order.class).add(Restrictions.eq("idorder", id)).uniqueResult();
		return order;
	}
	
	public boolean createOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		log.info("User saved successfully, Person Details=" + order);
		return true;
	}
}
