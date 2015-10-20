package com.mediabox.findpro.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.data.Menu;
import com.mediabox.findpro.form.CartForm;
import com.mediabox.findpro.service.CategoryService;
import com.mediabox.findpro.service.MenuService;

@Controller
public class CartController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	private MenuService menuService;
	private CategoryService categoryService;
	
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
	
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public ModelAndView cartGet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<Menu, Integer> cartItemList = this.getItemListFromCart(request);
		mav.addObject("cartItemList", cartItemList);
		mav.addObject("total", this.calculateTotal(cartItemList));
		mav.setViewName("cart");
		return mav;
	}
	
	@RequestMapping(value = "updateCart", method = RequestMethod.POST)
	public ModelAndView updateCartPost( HttpServletRequest request, @ModelAttribute(CART_FORM) CartForm cartForm) {
		ModelAndView mav = new ModelAndView();
		int menuId = cartForm.getMenuid();
		if (menuId==0) {
			HttpSession session = request.getSession();
			session.removeAttribute("cart");
		} else {
			int count = cartForm.getCount();
			this.updateItemListInCart(request, menuId, count);
		}
		Map<Menu, Integer> cartItemList = this.getItemListFromCart(request);
		mav.addObject("cartItemList", cartItemList);
		mav.addObject("total", this.calculateTotal(cartItemList));
		mav.setViewName("redirect:cart");
		return mav;
	}
	
	public void updateItemListInCart(HttpServletRequest request, int menuId, int count) {
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("cart") != null) {
			Map<Integer, Integer> cart = (Map<Integer, Integer>)session.getAttribute("cart");
			if (cart != null) {
				if (count == 0) {
					cart.remove(menuId);
				} else {
					cart.put(menuId, count);
				}
				session.setAttribute("cart", cart);
			}
		}
	}
	
	public Map<Menu, Integer> getItemListFromCart(HttpServletRequest request) {
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
	
	public double calculateTotal(Map<Menu, Integer> cartItemList) {
		BigDecimal sum = new BigDecimal(0);
		if (cartItemList != null) {
			for (Map.Entry<Menu, Integer> cartItem : cartItemList.entrySet()) {
				sum = sum.add(cartItem.getKey().getUnitPrice().multiply(new BigDecimal(cartItem.getValue())));
			}
		}
		return sum.doubleValue();
	}
}
