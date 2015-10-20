package com.mediabox.findpro.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mediabox.findpro.dao.AddressDao;
import com.mediabox.findpro.dao.CategoryDao;
import com.mediabox.findpro.dao.MenuDao;
import com.mediabox.findpro.dao.OrderDao;
import com.mediabox.findpro.dao.UserHome;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.mediabox.findpro" })
@EnableTransactionManagement
@Import({ WebSecurityConfig.class })
public class SpringWebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(DataSource dataSource) {
	    HibernateTransactionManager htm = new HibernateTransactionManager();
	    htm.setSessionFactory(sessionFactory());
	    return htm;
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/findpro");
	    dataSource.setUsername("root");
	    dataSource.setPassword("User@123");
	    return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() {
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
	    
//	    sessionBuilder.addAnnotatedClasses(User.class);
//	    sessionBuilder.addAnnotatedClasses(Company.class);
//	    sessionBuilder.addAnnotatedClasses(Employee.class);
//	    sessionBuilder.addAnnotatedClasses(Project.class);
//	    sessionBuilder.addAnnotatedClasses(Contact.class);
	    sessionBuilder.addProperties(getHibernateProperties());
	    sessionBuilder.scanPackages("com.mediabox.findpro.data");
	    SessionFactory sessionFactory = sessionBuilder.buildSessionFactory();
	    return sessionFactory;
	}
	
	@Autowired
	@Bean(name = "userDao")
	public UserHome getUserDao(SessionFactory sessionFactory) {
	    return new UserHome(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "menuDao")
	public MenuDao getMenuDao(SessionFactory sessionFactory) {
	    return new MenuDao(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "addressDao")
	public AddressDao getAddressDao(SessionFactory sessionFactory) {
	    return new AddressDao(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "orderDao")
	public OrderDao getOrderDao(SessionFactory sessionFactory) {
	    return new OrderDao(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "categoryDao")
	public CategoryDao getCategoryDao(SessionFactory sessionFactory) {
	    return new CategoryDao(sessionFactory);
	}
	
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//	    List<String> mappingResources = new ArrayList<>();
//	    mappingResources.add("classpath:com/mediabox/findpro/data/Company.hbm.xml");
//	    File file = new File("classpath:/hibernate/User.hbm.xml");
//	    System.out.println(file.getAbsolutePath());
//	    System.out.println(file.exists());
//	    mappingResources.add("hibernate/User.hbm.xml");
//	    mappingResources.add("hibernate/Contact.hbm.xml");
//	    mappingResources.add("hibernate/Project.hbm.xml");
//	    mappingResources.add("hibernate/Employee.hbm.xml");
//	    properties.put("mappingResources", mappingResources);
//	    properties.put("mappingLocations", "classpath:com/mediabox/findpro/data/*.hbm.xml");
	    return properties;
	}
}
