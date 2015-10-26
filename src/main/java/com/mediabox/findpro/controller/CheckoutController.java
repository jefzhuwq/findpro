package com.mediabox.findpro.controller;

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

import com.mediabox.findpro.data.AddressBook;
import com.mediabox.findpro.data.Menu;
import com.mediabox.findpro.data.Payment;
import com.mediabox.findpro.data.User;
import com.mediabox.findpro.service.AccountService;
import com.mediabox.findpro.service.AddressService;
import com.mediabox.findpro.service.PaymentService;

@Controller
public class CheckoutController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	private AccountService accountService;
	private AddressService addressService;
	private PaymentService paymentService;
	
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
	
	@Autowired(required = true)
	@Qualifier(value = "paymentService")
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView checkoutGet(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		String viewNameLogin = this.getRedirect("login?redirect=checkout");
		if (session != null && session.getAttribute("sessionId") != null) {
			String sessionId = String.valueOf(session.getAttribute("sessionId"));
			User user = this.accountService.getUserBySessionId(sessionId);
			if (user!=null) {
				// get address info
				AddressBook address = this.addressService.getUserDefaultAddress(user.getUserid());
				mav.addObject("address", address);
				// get payment info
				Payment payment = this.paymentService.getUserDefaultPayment(user.getUserid());
				mav.addObject("payment", payment);
				// get address list
				List<AddressBook> addressList = this.addressService.getAddressByUserId(user.getUserid());
				mav.addObject("addressList", addressList);
				// get payment list
				List<Payment> paymentList = this.paymentService.getPaymentByUserId(user.getUserid());
				mav.addObject("paymentList", paymentList);
				// get cart item list
				Map<Menu, Integer> cartItemList = this.getItemListFromCart(request);
				mav.addObject("cartItemList", cartItemList);
				// get total
				mav.addObject("total", this.calculateTotal(cartItemList));
				mav.setViewName("checkout");
			} else {
				mav.setViewName(viewNameLogin);
			}
		} else {
			mav.setViewName(viewNameLogin);
		}
		return mav;
	}
}
