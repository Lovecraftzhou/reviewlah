package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Category;

import java.util.ArrayList;

public interface CategoryDao {
    ArrayList<Category> selectAllCategory();
    Category selectCategoryByName(String category_name);
    Category selectCategoryById(int category_id);
    void insertCategory(String category_name);
    void updateCategory(Category category);
    void deleteCategoryByName(String category_name);
}
