package com.mediabox.findpro.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.form.LoginForm;
import com.mediabox.findpro.service.MyUserDetailsService;

@Controller
public class LoginController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//	private MyUserDetailsService userService;
	
//	@Autowired(required = true)
//	@Qualifier(value = "userDetailsService")
//	public void setUserService(MyUserDetailsService userService) {
//		this.userService = userService;
//	}
	
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//    public ModelAndView loginPost(Model model, @ModelAttribute(LOGIN_FORM) LoginForm loginForm) {
//        
//		String sessionID = null;
//		try {
			//sessionID = userService.login(loginForm.getUsername(), loginForm.getPassword(), false);
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (sessionID != null && sessionID != "") {
//	        ModelAndView mav = new ModelAndView("/menu");
//	        mav.addObject("sessionID", sessionID);
//	        return mav;
//        } else {
//        	ModelAndView mav = new ModelAndView("/login");
//        	List<String> errorList = new ArrayList<>();
//        	errorList.add("Something bad happen");
//        	mav.addObject("error", errorList);
//        	return mav;
//        }
//    }
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
//	@RequestMapping(value = "login", method = RequestMethod.GET)
//	public ModelAndView loginGet(@RequestParam(value = "error", required = false) String error,
//			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
//		ModelAndView model = new ModelAndView();
//		if (error != null) {
//			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
//		}
//
//		if (logout != null) {
//			model.addObject("msg", "You've been logged out successfully.");
//		}
//		model.setViewName("login");
//
//		return model;
//	}
	
	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}
	
	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String accountGet() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return "account";
	}
	
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String menuGet() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return "menu";
	}
	
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cartGet() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return "cart";
	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}
	
	// for 403 access denied page
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public ModelAndView accesssDenied() {

			ModelAndView model = new ModelAndView();

			// check if user is login
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				System.out.println(userDetail);

				model.addObject("username", userDetail.getUsername());

			}

			model.setViewName("403");
			return model;

		}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String checkoutGet() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return "checkout";
	}
}
