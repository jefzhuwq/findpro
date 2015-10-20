package com.mediabox.findpro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.data.Order;
import com.mediabox.findpro.data.OrderItem;
import com.mediabox.findpro.data.User;
import com.mediabox.findpro.service.AccountService;
import com.mediabox.findpro.service.CategoryService;
import com.mediabox.findpro.service.MenuService;
import com.mediabox.findpro.service.OrderService;

@Controller
public class OrderController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private MenuService menuService;
	private CategoryService categoryService;
	private AccountService accountService;
	private OrderService orderService;
	
	@Autowired(required = true)
	@Qualifier(value = "accountService")
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "orderService")
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "categoryService")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value = "order", method = RequestMethod.GET)
	public ModelAndView orderGet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if (session != null) {
			String sessionId = session.getAttribute("sessionId").toString();
			if (sessionId != null) {
				User user = this.accountService.getUserBySessionId(sessionId);
				if (user != null) {
					Order order = new Order();
					Date orderTime = new Date();
					order.setOrderTime(orderTime);
					order.setUpdateTime(orderTime);
					order.setTotal(100L);
					order.setUserId(user.getUserid());
					// set order items
					List<OrderItem> orderItems = this.getOrderItems(request);
					order.setOrderItems(new HashSet<OrderItem>(orderItems));
					for (OrderItem orderItem : orderItems) {
						orderItem.setOrder(order);
					}
					boolean result = this.orderService.placeOrder(order);
					if (result) {
						session.removeAttribute("cart");
						mav.setViewName("order");
					}
				}
			}
		}
		return mav;
	}
	
	public List<OrderItem> getOrderItems(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<OrderItem> orderItemList = new ArrayList<>();
		if (session != null) {
			Map<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
			for (Map.Entry<Integer, Integer> item : cart.entrySet()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setMenuId(item.getKey());
				orderItem.setCount(item.getValue());
				orderItemList.add(orderItem);
			}
		}
		return orderItemList;
	}
	
	@RequestMapping(value = "order", method = RequestMethod.POST)
	public ModelAndView orderPost(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order");
		return mav;
	}
}
