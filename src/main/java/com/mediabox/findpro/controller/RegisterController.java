package com.mediabox.findpro.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.data.User;
import com.mediabox.findpro.form.LoginForm;
import com.mediabox.findpro.service.UserService;
import com.mediabox.findpro.util.EncryptionHelper;

@Controller
public class RegisterController extends BasicController {
	private UserService userService;
	EncryptionHelper helper = new EncryptionHelper();
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView registerPost(Model model, @Valid @ModelAttribute(REGISTER_FORM) LoginForm loginForm, BindingResult bindingResult) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			int errorCount = bindingResult.getErrorCount();
			List<?> errorList = bindingResult.getAllErrors();
			ModelAndView mav = new ModelAndView("/register");
//			mav.addObject("error", errorCount);
			mav.addObject("error", errorList);
			return mav;
        }
		
		// if no error, then start register
		String userName = loginForm.getUsername();
        String password = loginForm.getPassword();
        // create user
        User user = new User();
        String encPassword = helper.encrypt(password);
        user.setUsername(userName);
        user.setPassword(encPassword);
        userService.register(user);
        // login automatically after register
        String sessionID = userService.login(userName, encPassword, true);
        // navigate to my account page
        ModelAndView mav = new ModelAndView("/home");
        mav.addObject("sessionID", sessionID);
        
        return mav;
    }
}
