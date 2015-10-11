package com.mediabox.findpro.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mediabox.findpro.data.User;
import com.mediabox.findpro.form.RegisterForm;
import com.mediabox.findpro.service.AccountService;
import com.mediabox.findpro.service.MyUserDetailsService;
import com.mediabox.findpro.util.EncryptionHelper;

@Controller
public class RegisterController extends BasicController {
	private AccountService accountService;
	EncryptionHelper helper = new EncryptionHelper();
	
	@Autowired(required = true)
	@Qualifier(value = "accountService")
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public ModelAndView registerGet(@RequestParam(value = "error", required = false) String error, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		model.setViewName("register");

		return model;
	}
	
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
	
	@RequestMapping(value = { "register", "signup" }, method = RequestMethod.POST)
    public ModelAndView registerPost(@Valid @ModelAttribute(REGISTER_FORM) RegisterForm registerForm, BindingResult bindingResult) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			int errorCount = bindingResult.getErrorCount();
			List<?> errorList = bindingResult.getAllErrors();
			ModelAndView mav = new ModelAndView("/register");
//			mav.addObject("error", errorCount);
			mav.addObject("error", errorList);
			return mav;
        }
		
		// if no error, then start register
		String userName = registerForm.getUsername();
        String password = registerForm.getPassword();
        String firstname = registerForm.getFirstname();
        String lastname = registerForm.getLastname();
        String email = registerForm.getEmail();
        // create user
        User user = new User();
        String encPassword = helper.encrypt(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(email);
//        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(encPassword);
        user.setEnabled(true);
        accountService.register(user);
        // login automatically after register
        String sessionID = accountService.login(userName, encPassword, true);
//        this.userDetailsService.loadUserByUsername(user.getEmail());
        // navigate to my account page
        ModelAndView mav = new ModelAndView("/home");
        mav.addObject("sessionID", sessionID);
        return mav;
    }
}
