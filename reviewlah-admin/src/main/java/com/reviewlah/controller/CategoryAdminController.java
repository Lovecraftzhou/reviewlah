package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteCategoryByNameRequest;
import com.reviewlah.controller.form.InsertCategoryRequest;
import com.reviewlah.controller.form.UpdateCategoryRequest;
import com.reviewlah.remote.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/admin/merchant/category"})
public class CategoryAdminController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping({"showAll"})
    public RCode selectAllCategory() {
        return this.categoryService.selectAllCategory();
    }
    @GetMapping("/{category_name}")
    public RCode selectCategoryByName(@PathVariable String category_name) {
        return this.categoryService.selectCategoryByName(category_name);
    }
    @PostMapping("/insert")
    public RCode insertCategory(@RequestBody InsertCategoryRequest request) {
        return this.categoryService.insertCategory(request);
    }
    @PostMapping("/update")
    public RCode updateCategory(@RequestBody UpdateCategoryRequest request) {
        return this.categoryService.updateCategory(request);
    }
    @PostMapping("/delete")
    public RCode deleteCategoryByName(@RequestBody DeleteCategoryByNameRequest request) {
        return this.categoryService.deleteCategoryByName(request);
    }
}
