package com.reviewlah.controller;

import com.reviewlah.db.pojo.Category;
import com.reviewlah.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping({"/category"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping({"showAll"})
    public ArrayList<String> selectAllCategory() {
        ArrayList<Category> list = this.categoryService.selectAllCategory();
        ArrayList<String> res = new ArrayList<>();
        for(Category tmp : list) {
            String name = tmp.getCategory_name();
            res.add(name);
        }
        return res;
    }
}
