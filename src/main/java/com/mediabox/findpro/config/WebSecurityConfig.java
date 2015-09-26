package com.mediabox.findpro.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.mediabox.findpro.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
		
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
//        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password from user where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user_roles where username=?");
//		auth.inMemoryAuthentication().withUser("admin").password("password")
//				.roles("ADMIN", "USER").and().withUser("user")
//				.password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
		.antMatchers("/account/**").access("hasRole('ROLE_ADMIN')")
//		.antMatchers("/register").permitAll().anyRequest().authenticated()
		.and()
		  .formLogin().loginPage("/login").failureUrl("/login?error")
		  .usernameParameter("username").passwordParameter("password")
		.and()
		  .logout().logoutSuccessUrl("/login?logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf();
	}
	
//	@Bean
//	public DataSource getDataSource() {
//	    BasicDataSource dataSource = new BasicDataSource();
//	    dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//	    dataSource.setUrl(env.getProperty("jdbc.url"));
//	    dataSource.setUsername(env.getProperty("jdbc.username"));
//	    dataSource.setPassword(env.getProperty("jdbc.password"));
//	    return dataSource;
//	}
}

