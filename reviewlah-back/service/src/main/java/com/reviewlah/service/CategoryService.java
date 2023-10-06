package com.reviewlah.service;

import com.reviewlah.db.pojo.Category;

import java.util.ArrayList;

public interface CategoryService {
    ArrayList<String> selectAllCategory();
    int selectCategoryIdByName(String category_name);
    void insertCategory(String category_name);
    void updateCategory(Category category);
    void deleteCategoryByName(String category_name);
}
