package com.reviewlah.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewlah.common.util.RCode;
import com.reviewlah.db.pojo.Category;
import com.reviewlah.service.CategoryService;

@RestController
@RequestMapping({"/merchant/category"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping({"showAll"})
    public RCode selectAllCategory() {
        ArrayList<Category> list = this.categoryService.selectAllCategory();
        ArrayList<String> res = new ArrayList<>();
        for(Category tmp : list) {
            String name = tmp.getCategory_name();
            res.add(name);
        }
        return RCode.ok().put("list", res);
    }

    @GetMapping("/{category_name}")
    public RCode selectCategoryByName(@PathVariable String category_name) {
        Category category = categoryService.selectCategoryByName(category_name);
        return RCode.ok().put("category", category);
    }
}
