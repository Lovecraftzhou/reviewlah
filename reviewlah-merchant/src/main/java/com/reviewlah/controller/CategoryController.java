package com.reviewlah.controller;

import java.util.ArrayList;

import com.reviewlah.controller.form.DeleteCategoryByNameRequest;
import com.reviewlah.controller.form.InsertCategoryRequest;
import com.reviewlah.controller.form.UpdateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/insert")
    public RCode insertCategory(@RequestBody InsertCategoryRequest request) {
        String category_name = request.getCategory_name();
        Category category = this.categoryService.selectCategoryByName(category_name);
        if(category == null) {
            this.categoryService.insertCategory(category_name);
            return RCode.ok("successful");
        }
        else {
            System.out.println("Category Does Already Exist");
            return RCode.error("Category Does Already Exist");
        }
    }
    @PostMapping("/update_category")
    public RCode updateCategory(@RequestBody UpdateCategoryRequest request) {
        int category_id = request.getCategory_id();
        String category_name = request.getCategory_name();
        Category new_category = new Category();
        Category category = this.categoryService.selectCategoryById(category_id);
        Category tmp = this.categoryService.selectCategoryByName(category_name);
        if(category != null) {
            if(tmp == null) {
                new_category.setCategory_id(category_id);
                new_category.setCategory_name(category_name);
                this.categoryService.updateCategory(new_category);
                return RCode.ok("successful");
            }
            else {
                System.out.println("Category Does Already Exist");
                return RCode.error("Category Does Already Exist");
            }
        }
        else {
            System.out.println("Category Does Not Exist");
            return RCode.error("Category Does Not Exist");
        }
    }
    @PostMapping("/delete")
    public RCode deleteCategoryByName(@RequestBody DeleteCategoryByNameRequest request) {
        String category_name = request.getCategory_name();
        Category category = this.categoryService.selectCategoryByName(category_name);
        if(category != null) {
            this.categoryService.deleteCategoryByName(category_name);
            return RCode.ok("successful");
        }
        else {
            System.out.println("Category Does Not Exist");
            return RCode.error("Category Does Not Exist");
        }
    }
}
