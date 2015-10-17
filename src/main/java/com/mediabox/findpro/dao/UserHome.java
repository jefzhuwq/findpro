package com.mediabox.findpro.dao;

// Generated 2015-8-16 15:04:42 by Hibernate Tools 4.3.1

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mediabox.findpro.data.User;
import com.mediabox.findpro.util.EncryptionHelper;

/**
 * Home object for domain model class User.
 * @see com.mediabox.findpro.dao.User
 * @author Hibernate Tools
 */
@Repository("userDao")
public class UserHome {
	private static final Log log = LogFactory.getLog(UserHome.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserHome() {
	}
	
	public UserHome(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	private EncryptionHelper encHelper = new EncryptionHelper();

	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) sessionFactory.getCurrentSession().get(
					"com.mediabox.findpro.dao.User", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.mediabox.findpro.dao.User")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public boolean addUser(User user) {
		List<User> userList = this.getUserByUserName(user.getUsername());
		if(userList.size()!=0) {
			return false;
		}
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		log.info("User saved successfully, Person Details=" + user);
		return true;
	}
	
	private List<User> getUserByUserName(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createCriteria(User.class).add(Restrictions.eq("username", userName)).list();
		return userList;
	}
	
	public User findBySessionId(String sessionId) {
		log.debug("getting User instance with sessionId: " + sessionId);
		List<User> users = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		users = session.createCriteria(User.class).add(Restrictions.eq("sessionid", sessionId)).list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	public String login(String userName, String password, boolean isEncrypted) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Session session = this.sessionFactory.getCurrentSession();
		if (!isEncrypted) {
			password = encHelper.encrypt(password);
		}
		// sample of how to use hql
//		Query query = session.createSQLQuery(
//				"select * from User u where u.username = :userName and u.password = :password")
//				.addEntity(User.class)
//				.setParameter("userName", userName).setParameter("password", password);
//		User user = (User)query.uniqueResult();
		// here's the samlpe using criteria
		User user = (User)session.createCriteria(User.class).add(Restrictions.eq("username", userName)).add(Restrictions.eq("password", password)).uniqueResult();
		if (user != null) {
			String sessionID = UUID.randomUUID().toString();
			user.setSessionid(sessionID);
			session.save(user);
			return sessionID;
		} else {
			return null;
		}
	}
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public User getUserInfo(String username){
    	String sql = "SELECT u.username name, u.password pass, 'USER' role FROM "+
    			     "comp_users u INNER JOIN comp_authorities a on u.username=a.username WHERE "+
    			     "u.enabled =1 and u.username = ?";
    	User userInfo = (User)jdbcTemplate.queryForObject(sql, new Object[]{username},
    		new RowMapper<User>() {
	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	                User user = new User();
	                user.setUsername(rs.getString("name"));
	                user.setPassword(rs.getString("pass"));
//	                user.setRole(rs.getString("role"));
	                return user;
	            }
        });
    	return userInfo;
    }
	
	@SuppressWarnings("unchecked")
	public User findByUserName(String userName) {
		List<User> users = new ArrayList<>();
		users = sessionFactory.getCurrentSession().createQuery("from User where username=?").setParameter(0, userName)
				.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
}
