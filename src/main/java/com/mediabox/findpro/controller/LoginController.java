package com.mediabox.findpro.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.HomeController;
import com.mediabox.findpro.form.LoginForm;
import com.mediabox.findpro.service.UserService;

@Controller
public class LoginController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView loginGet(@ModelAttribute(LOGIN_FORM) LoginForm loginForm) {
    	
        String message = "We logged in...";
        
        ModelAndView mav = new ModelAndView("/");
        
        String sessionID = userService.login(loginForm.getUsername(), loginForm.getPassword());
        mav.addObject("sessionID", sessionID);
        mav.addObject("message", message);
        
        return mav;
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		return "login";
	}
}
