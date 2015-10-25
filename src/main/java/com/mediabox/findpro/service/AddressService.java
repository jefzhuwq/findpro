package com.mediabox.findpro.service;

import java.util.List;

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
	
	@Transactional
    public List<AddressBook> getAddressByUserId(int userId) {
        return this.addressDao.getAddressByUserId(userId);
    }
	
	@Transactional
    public AddressBook getAddressByIdAndUserId(int id, int userId) {
        return this.addressDao.getAddressByIdAndUserId(id, userId);
    }
	
	@Transactional
    public void save(AddressBook address) {
        this.addressDao.persist(address);
    }
	
	@Transactional
    public void deleteAddress(int id) {
        this.addressDao.deleteAddress(id);
    }
}
