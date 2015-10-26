package com.mediabox.findpro.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.mediabox.findpro.data.Menu;
import com.mediabox.findpro.service.MenuService;

public class BasicController {
	public static final String LOGIN_FORM = "loginForm";
	public static final String REGISTER_FORM = "registerForm";
	public static final String CART_FORM = "cartForm";
	public static final String ADDRESS_FORM = "addressForm";
	public static final String PAYMENT_FORM = "paymentForm";
	private static final String REDIRECT = "redirect:"; 
	private MenuService menuService;
	
	@Autowired(required = true)
	@Qualifier(value = "menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	protected String getRedirect(String url) {
		if (!StringUtils.isEmpty(url)) {
			return REDIRECT + url;
		} else {
			return REDIRECT + "home";
		}
	}
	
	protected Map<Menu, Integer> getItemListFromCart(HttpServletRequest request) {
		Map<Menu, Integer> cartItemList = new HashMap<>();
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("cart") != null) {
			Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
			if (cart != null) {
				for (int menuId : cart.keySet()) {
					cartItemList.put(this.menuService.getMenuById(menuId), cart.get(menuId));
				}
			}
		}
		return cartItemList;
	}
	
	protected double calculateTotal(Map<Menu, Integer> cartItemList) {
		BigDecimal sum = new BigDecimal(0);
		if (cartItemList != null) {
			for (Map.Entry<Menu, Integer> cartItem : cartItemList.entrySet()) {
				sum = sum.add(cartItem.getKey().getUnitPrice().multiply(new BigDecimal(cartItem.getValue())));
			}
		}
		return sum.doubleValue();
	}
}
