package com.reviewlah.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reviewlah.common.util.RCode;
import com.reviewlah.db.pojo.Category;

@FeignClient("merchant")
public interface CategoryService {

    @GetMapping("/merchant/category/{category_name}")
    RCode selectCategoryByName(@PathVariable String category_name);
}
