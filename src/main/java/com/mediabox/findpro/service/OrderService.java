package com.mediabox.findpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.findpro.dao.OrderDao;
import com.mediabox.findpro.data.Order;
import com.mediabox.findpro.data.OrderItem;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Transactional
    public List<OrderItem> getOrderItemByOrderId(int orderId) {
        return this.orderDao.getOrderItemByOrderId(orderId);
    }
	
	@Transactional
    public boolean placeOrder(Order order) {
        return this.orderDao.createOrder(order);
    }
}
