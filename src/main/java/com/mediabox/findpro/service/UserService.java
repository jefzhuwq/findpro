package com.mediabox.findpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.findpro.dao.UserHome;
import com.mediabox.findpro.data.User;

@Service
public class UserService {
	private UserHome userDAO;
	
	@Autowired
	public void setUserDAO(UserHome userDAO) {
        this.userDAO = userDAO;
    }
	
	@Transactional
	public String login(String userName, String password) {
		return "";
	}
	
	@Transactional
    public void register(User user) {
        this.userDAO.addUser(user);
    }
}
