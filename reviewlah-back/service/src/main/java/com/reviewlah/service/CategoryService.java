package com.reviewlah.service;

import com.reviewlah.db.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public interface CategoryService {
    ArrayList<Category> selectAllCategory();
    int selectCategoryIdByName(String category_name);
    void insertCategory(String category_name);
    void updateCategory(Category category);
    void deleteCategoryByName(String category_name);
}
