package com.mediabox.findpro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.data.Payment;
import com.mediabox.findpro.data.User;
import com.mediabox.findpro.form.PaymentForm;
import com.mediabox.findpro.service.AccountService;
import com.mediabox.findpro.service.PaymentService;

@Controller
public class PaymentController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	private AccountService accountService;
	private PaymentService paymentService;
	
	@Autowired(required = true)
	@Qualifier(value = "accountService")
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "paymentService")
	public void setPaymentervice(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@RequestMapping(value = "payment", method = RequestMethod.GET)
	public ModelAndView cartGet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			// get default address
			Payment payment = this.paymentService.getUserDefaultPayment(user.getUserid());
			mav.addObject("payment", payment);
			
			// get all address
			List<Payment> paymentList = this.paymentService.getPaymentByUserId(user.getUserid());
			mav.addObject("paymentList", paymentList);
		
			mav.setViewName("payment/payment");
		} else {
			mav.setViewName(this.getRedirect("login?redirect=payment"));
		}
		return mav;
	}
	
	@RequestMapping(value = "editPayment", method = RequestMethod.GET)
	public ModelAndView editAddressGet(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			if (!StringUtils.isEmpty(id)) {
				Payment payment = this.paymentService.getPaymentByIdAndUserId(Integer.parseInt(id), user.getUserid());
				if (payment != null) {
					mav.addObject("payment", payment);
				}
			}
			mav.setViewName("payment/editPayment");
		}
		return mav;
	}
	
	@RequestMapping(value = "editPayment", method = RequestMethod.POST)
	public ModelAndView editPaymentPost(HttpServletRequest request, @ModelAttribute(PAYMENT_FORM) PaymentForm paymentForm, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			Payment payment = null;
			if (!StringUtils.isEmpty(id)) {
				payment = this.paymentService.getPaymentByIdAndUserId(Integer.parseInt(id), user.getUserid());
			}
			if (payment == null) {
				payment = new Payment();
				payment.setUserId(user.getUserid());
			}
			this.paymentService.save(payment);
		}
		mav.setViewName(this.getRedirect("address"));
		return mav;
	}
	
	@RequestMapping(value = "selectPayment", method = RequestMethod.GET)
	public ModelAndView selectAddressGet(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			if (!StringUtils.isEmpty(id)) {
				// set other address non-primary()
				this.setPaymentNonPrimary(user.getUserid());
				// make selected address primary
				Payment payment = this.paymentService.getPaymentByIdAndUserId(Integer.parseInt(id), user.getUserid());
				payment.setIsPrimary(true);
				this.paymentService.save(payment);
			}
			mav.setViewName(this.getRedirect("checkout"));
		}
		return mav;
	}
	
	@RequestMapping(value = "deletePayment", method = RequestMethod.GET)
	public ModelAndView deleteAddressGet(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			Payment payment = this.paymentService.getPaymentByIdAndUserId(Integer.parseInt(id), user.getUserid());
			if (payment != null) {
				mav.addObject("payment", payment);
			}
			mav.setViewName("payment/deletePayment");
		}
		return mav;
	}
	
	@RequestMapping(value = "deletePayment", method = RequestMethod.POST)
	public ModelAndView deleteAddressPost(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			Payment payment = this.paymentService.getPaymentByIdAndUserId(Integer.parseInt(id), user.getUserid());
			if (payment != null) {
				this.paymentService.deletePayment(payment.getIdpayment());
				mav.setViewName(this.getRedirect("payment"));
			}
		}
		return mav;
	}
	
	public User getUserBySession(HttpSession session) {
		User user = null;
		if (session != null && session.getAttribute("sessionId") != null) {
			String sessionId = String.valueOf(session.getAttribute("sessionId"));
			user = this.accountService.getUserBySessionId(sessionId);
		}
		return user;
	}
	
	public void setPaymentNonPrimary(int userId) {
		// set other address to non-primary
		List<Payment> paymentList = this.paymentService.getPaymentByUserId(userId);
		for (Payment payment : paymentList) {
			payment.setIsPrimary(false);
			this.paymentService.save(payment);
		}
	}
}
