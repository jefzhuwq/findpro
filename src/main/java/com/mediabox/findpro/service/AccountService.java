package com.mediabox.findpro.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.findpro.dao.UserHome;

@Service
public class AccountService {
	@Autowired
	private UserHome userDao;
	
	@Transactional
	public String login(String userName, String password, boolean isEncrypted) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return this.userDao.login(userName, password, isEncrypted);
	}
	
	@Transactional
    public void register(com.mediabox.findpro.data.User user) {
        this.userDao.addUser(user);
    }
}
