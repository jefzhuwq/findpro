package com.mediabox.findpro.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediabox.findpro.data.Category;
import com.mediabox.findpro.data.User;

@Repository("categoryDao")
public class CategoryDao {
	private static final Log log = LogFactory.getLog(CategoryDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CategoryDao() {
	}
	
	public CategoryDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Category> getAllCategory() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Category> categoryList = session.createCriteria(Category.class).addOrder(Order.asc("idcategory")).list();
//		Query query = session.createQuery("from Category");
//		List<Category> categoryList = query.list();
		return categoryList;
	}
	
	public Category getCategoryById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Category category = (Category)session.createCriteria(Category.class).add(Restrictions.eq("idcategory", id)).uniqueResult();
		return category;
	}
}
