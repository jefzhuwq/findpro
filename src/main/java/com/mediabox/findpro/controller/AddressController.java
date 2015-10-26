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

import com.mediabox.findpro.data.AddressBook;
import com.mediabox.findpro.data.User;
import com.mediabox.findpro.form.AddressForm;
import com.mediabox.findpro.service.AccountService;
import com.mediabox.findpro.service.AddressService;

@Controller
public class AddressController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
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
	
	@RequestMapping(value = "address", method = RequestMethod.GET)
	public ModelAndView cartGet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			// get default address
			AddressBook address = this.addressService.getUserDefaultAddress(user.getUserid());
			mav.addObject("address", address);
			
			// get all address
			List<AddressBook> addressList = this.addressService.getAddressByUserId(user.getUserid());
			mav.addObject("addressList", addressList);
		
			mav.setViewName("address/address");
		} else {
			mav.setViewName(this.getRedirect("login?redirect=address"));
		}
		return mav;
	}
	
	@RequestMapping(value = "editAddress", method = RequestMethod.GET)
	public ModelAndView editAddressGet(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			if (!StringUtils.isEmpty(id)) {
				AddressBook address = this.addressService.getAddressByIdAndUserId(Integer.parseInt(id), user.getUserid());
				if (address != null) {
					mav.addObject("address", address);
				}
			}
			mav.setViewName("address/editAddress");
		}
		return mav;
	}
	
	@RequestMapping(value = "editAddress", method = RequestMethod.POST)
	public ModelAndView editAddressPost(HttpServletRequest request, @ModelAttribute(ADDRESS_FORM) AddressForm addressForm, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			AddressBook address = null;
			if (!StringUtils.isEmpty(id)) {
				address = this.addressService.getAddressByIdAndUserId(Integer.parseInt(id), user.getUserid());
			}
			if (address == null) {
				address = new AddressBook();
				address.setUserId(user.getUserid());
			}
			address.setFirstName(addressForm.getFirstname());
			address.setLastName(addressForm.getLastname());
			address.setStreet(addressForm.getStreet());
			address.setCity(addressForm.getCity());
			address.setState(addressForm.getState());
			address.setZipcode(addressForm.getZipcode());
			this.addressService.save(address);
		}
		mav.setViewName(this.getRedirect("address"));
		return mav;
	}
	
	@RequestMapping(value = "selectAddress", method = RequestMethod.GET)
	public ModelAndView selectAddressGet(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			if (!StringUtils.isEmpty(id)) {
				// set other address non-primary()
				this.setAddressNonPrimary(user.getUserid());
				// make selected address primary
				AddressBook address = this.addressService.getAddressByIdAndUserId(Integer.parseInt(id), user.getUserid());
				address.setIsPrimary(true);
				this.addressService.save(address);
			}
			mav.setViewName(this.getRedirect("checkout"));
		}
		return mav;
	}
	
	@RequestMapping(value = "deleteAddress", method = RequestMethod.GET)
	public ModelAndView deleteAddressGet(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			AddressBook address = this.addressService.getAddressByIdAndUserId(Integer.parseInt(id), user.getUserid());
			if (address != null) {
				mav.addObject("address", address);
			}
			mav.setViewName("address/deleteAddress");
		}
		return mav;
	}
	
	@RequestMapping(value = "deleteAddress", method = RequestMethod.POST)
	public ModelAndView deleteAddressPost(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		User user = this.getUserBySession(request.getSession());
		if (user != null) {
			AddressBook address = this.addressService.getAddressByIdAndUserId(Integer.parseInt(id), user.getUserid());
			if (address != null) {
				this.addressService.deleteAddress(address.getIdaddressBook());
				mav.setViewName(this.getRedirect("address"));
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
	
	public void setAddressNonPrimary(int userId) {
		// set other address to non-primary
		List<AddressBook> addressList = this.addressService.getAddressByUserId(userId);
		for (AddressBook address : addressList) {
			address.setIsPrimary(false);
			this.addressService.save(address);
		}
	}
}
