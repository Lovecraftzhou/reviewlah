package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteCategoryByNameRequest;
import com.reviewlah.controller.form.InsertCategoryRequest;
import com.reviewlah.controller.form.UpdateCategoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("merchant")
public interface CategoryService {
    @GetMapping({"/merchant/category/showAll"})
    RCode selectAllCategory();
    @GetMapping("/merchant/category/{category_name}")
    RCode selectCategoryByName(@PathVariable String category_name);
    @PostMapping("/merchant/category/insert")
    RCode insertCategory(@RequestBody InsertCategoryRequest request);
    @PostMapping("/merchant/category/update_category")
    RCode updateCategory(@RequestBody UpdateCategoryRequest request);
    @PostMapping("/merchant/category/delete")
    RCode deleteCategoryByName(@RequestBody DeleteCategoryByNameRequest request);
}
