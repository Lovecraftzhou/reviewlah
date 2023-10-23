package com.reviewlah.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Category;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CategoryServiceTest {
    @Autowired
    private CategoryService service;

    @Test
    void test() {
        service.insertCategory("Test Category");
        Category category = service.selectCategoryByName("Test Category");
        assertNotNull(category);
        category.setCategory_name("Updated Category");
        service.updateCategory(category);
        category = service.selectCategoryByName("Updated Category");
        assertNotNull(category);
        ArrayList<Category> categories = service.selectAllCategory();
        assertFalse(categories.isEmpty());
        service.deleteCategoryByName("Updated Category");
    }
}