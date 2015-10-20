package com.mediabox.findpro.controller;

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

import com.mediabox.findpro.data.AddressBook;
import com.mediabox.findpro.data.User;
import com.mediabox.findpro.service.AccountService;
import com.mediabox.findpro.service.AddressService;

@Controller
public class CheckoutController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	private AccountService accountService;
	private AddressService addressService;
	
	@Autowired(required = true)
	@Qualifier(value = "accountService")
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "addressService")
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView checkoutGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		if (session != null && session.getAttribute("sessionId") != null) {
			String sessionId = String.valueOf(session.getAttribute("sessionId"));
			User user = this.accountService.getUserBySessionId(sessionId);
			if (user!=null) {
				// get address info
				AddressBook address = this.addressService.getUserDefaultAddress(user.getUserid());
				// get payment info
				mav.addObject("address", address);
				mav.setViewName("checkout");
			} else {
				mav.setViewName("redirect:login?redirect=checkout");
			}
		} else {
			mav.setViewName("redirect:login?redirect=checkout");
		}
		return mav;
	}
}
