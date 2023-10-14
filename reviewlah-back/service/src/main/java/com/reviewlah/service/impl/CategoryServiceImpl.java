package com.reviewlah.service.impl;

import com.reviewlah.db.dao.CategoryDao;
import com.reviewlah.db.pojo.Category;
import com.reviewlah.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    public ArrayList<Category> selectAllCategory() {
        return this.categoryDao.selectAllCategory();
    }
    public int selectCategoryIdByName(String category_name) {
        return this.categoryDao.selectCategoryIdByName(category_name);
    }
    public void insertCategory(String category_name) {
        this.categoryDao.insertCategory(category_name);
    }
    public void updateCategory(Category category) {
        this.categoryDao.updateCategory(category);
    }
    public void deleteCategoryByName(String category_name) {
        this.categoryDao.deleteCategoryByName(category_name);
    }
}
