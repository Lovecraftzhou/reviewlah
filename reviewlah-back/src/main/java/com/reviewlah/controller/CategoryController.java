package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.db.pojo.Category;
import com.reviewlah.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@Tag(name = "种类模块")
@RestController
@RequestMapping({"/category"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "获取所有种类")
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
}
