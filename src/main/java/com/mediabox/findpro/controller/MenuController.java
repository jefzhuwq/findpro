package com.mediabox.findpro.controller;

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
import com.mediabox.findpro.service.CategoryService;
import com.mediabox.findpro.service.MenuService;

@Controller
public class MenuController extends BasicController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	private MenuService menuService;
	private CategoryService categoryService;
	
	@Autowired(required = true)
	@Qualifier(value = "menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "categoryService")
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value = "addToCart", method = RequestMethod.POST)
    public ModelAndView addToCartPost(Model model, @ModelAttribute(LOGIN_FORM) LoginForm loginForm) {
		String sessionId = null;
        ModelAndView mav = new ModelAndView("/menu");
        mav.addObject("sessionId", sessionId);
        return mav;
    }
	
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public ModelAndView menuGet(@RequestParam(value = "cat", required = false) String cat) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categories", this.categoryService.getAllCategory());
		int categoryId = 1;
		try {
			categoryId = Integer.parseInt(cat);
		} catch (NumberFormatException e) {
		    System.out.println("Wrong number");
		}
		mav.addObject("selectedcategory", this.categoryService.getCategoryById(categoryId));
		mav.addObject("menus", this.menuService.getMenuByCategoryId(categoryId));
		mav.setViewName("menu");
		return mav;
	}
}
