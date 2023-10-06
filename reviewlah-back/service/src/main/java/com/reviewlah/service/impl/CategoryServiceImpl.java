package com.reviewlah.service.impl;

import com.reviewlah.db.dao.CategoryDao;
import com.reviewlah.db.pojo.Category;
import com.reviewlah.service.CategoryService;

import java.util.ArrayList;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;
    public ArrayList<String> selectAllCategory() {
        ArrayList<String> list = this.categoryDao.selectAllCategory();
        return list;
    }
    public int selectCategoryIdByName(String category_name) {
        int category_id = this.categoryDao.selectCategoryIdByName(category_name);
        return category_id;
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
