package com.reviewlah.service;

import com.reviewlah.db.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface CategoryService {
    ArrayList<Category> selectAllCategory();
    Category selectCategoryByName(String category_name);
    void insertCategory(String category_name);
    void updateCategory(Category category);
    void deleteCategoryByName(String category_name);
    Category selectCategoryById(int category_id);
}
