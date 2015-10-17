package com.mediabox.findpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.findpro.dao.AddressDao;
import com.mediabox.findpro.dao.UserHome;
import com.mediabox.findpro.data.AddressBook;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	
	@Transactional
    public AddressBook getUserDefaultAddress(int userId) {
        return this.addressDao.getDefaultAddress(userId);
    }
}
