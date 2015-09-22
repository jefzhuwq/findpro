package com.mediabox.findpro.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.form.LoginForm;
import com.mediabox.findpro.service.UserService;

@Controller
public class LoginController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView loginPost(Model model, @ModelAttribute(LOGIN_FORM) LoginForm loginForm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String sessionID = userService.login(loginForm.getUsername(), loginForm.getPassword(), false);
		if (sessionID != null && sessionID != "") {
	        ModelAndView mav = new ModelAndView("/home");
	        mav.addObject("sessionID", sessionID);
	        return mav;
        } else {
        	ModelAndView mav = new ModelAndView("/login");
        	List<String> errorList = new ArrayList<>();
        	errorList.add("Something bad happen");
        	mav.addObject("error", errorList);
        	return mav;
        }
    }
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	//public String loginGet(Locale locale, Model model) {
	public ModelAndView loginGet(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
		//return "login";
	}
	
	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String accountGet() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return "account";
	}
}
