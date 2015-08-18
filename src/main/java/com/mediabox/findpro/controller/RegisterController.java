package com.mediabox.findpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.data.User;
import com.mediabox.findpro.form.LoginForm;
import com.mediabox.findpro.service.UserService;

@Controller
public class RegisterController extends BasicController {
	private UserService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView loginGet(@ModelAttribute(REGISTER_FORM) LoginForm loginForm) {
    	
        String message = "We logged in...";
        
        ModelAndView mav = new ModelAndView("/");
        
        User user = new User();
        user.setUsername(loginForm.getUsername());
        user.setPassword(loginForm.getPassword());
        userService.register(user);
//        mav.addObject("sessionID", sessionID);
        mav.addObject("message", message);
        
        return mav;
    }
}
