package com.mediabox.findpro.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mediabox.findpro.dao.UserHome;

public class AuthenticationService implements UserDetailsService {
	@Autowired
	private UserHome userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
//		com.mediabox.findpro.data.User userInfo = userDAO.getUserInfo(username);
//		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
//		UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), 
//				userInfo.getPassword(), Arrays.asList(authority));
		return null;
	}
}
