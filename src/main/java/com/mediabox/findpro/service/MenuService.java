package com.mediabox.findpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.findpro.dao.MenuDao;
import com.mediabox.findpro.data.Menu;

@Service
public class MenuService {
	@Autowired
	private MenuDao menuDao;
	
	@Transactional
	public List<Menu> getMenuByCategoryId(int categoryId) {
		return this.menuDao.getMenuByCategoryId(categoryId);
	}
}
