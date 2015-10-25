package com.mediabox.findpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.findpro.dao.PaymentDao;
import com.mediabox.findpro.data.AddressBook;
import com.mediabox.findpro.data.Payment;

@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	
	@Transactional
    public Payment getUserDefaultPayment(int userId) {
        return this.paymentDao.getDefaultPayment(userId);
    }
	
	@Transactional
    public List<Payment> getPaymentByUserId(int userId) {
        return this.paymentDao.getPaymentByUserId(userId);
    }
	
	@Transactional
    public Payment getPaymentByIdAndUserId(int id, int userId) {
        return this.paymentDao.getPaymentByIdAndUserId(id, userId);
    }
	
	@Transactional
    public void save(Payment payment) {
        this.paymentDao.persist(payment);
    }
	
	@Transactional
    public void deletePayment(int id) {
        this.paymentDao.deletePayment(id);
    }
}
