package com.mediabox.findpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mediabox.findpro.dao.CategoryDao;
import com.mediabox.findpro.data.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public List<Category> getAllCategory() {
		return this.categoryDao.getAllCategory();
	}
	
	@Transactional
	public Category getCategoryById(int id) {
		return this.categoryDao.getCategoryById(id);
	}
}
